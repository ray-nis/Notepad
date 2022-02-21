package app.controllers;

import app.components.JFontChooser;
import app.views.MenuBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class MenuBarController {
    MenuBar view;
    JTextArea textArea;
    File file;
    boolean documentChanged = false;
    UndoManager undoManager;

    public MenuBarController(MenuBar bar, JTextArea textArea) {
        this.view = bar;
        this.textArea = textArea;

        createFileMenuListeners();
        createTextAreaListeners();
        createEditMenuListeners();
        createFormatMenuListeners();
    }

    private void createEditMenuListeners() {
        createUndo();
        view.getEditMenu().addMenuListener(new DisableItemsAction());
        view.getUndoItem().addActionListener(new UndoAction());
        view.getRedoItem().addActionListener(new RedoAction());
        view.getPasteItem().addActionListener(new PasteAction());
        view.getCopyItem().addActionListener(new CopySelectedAction());
        view.getCutItem().addActionListener(new CutSelectedAction());
        view.getDeleteItem().addActionListener(new DeleteSelectedAction());
        view.getSelectAllItem().addActionListener(new SelectAllAction());
        view.getFindAndReplaceItem().addActionListener(new FindReplaceAction());
    }

    private void createUndo() {
        undoManager = new UndoManager();
        Document doc = textArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undoManager.addEdit(evt.getEdit());
            }
        });

        textArea.getActionMap().put("Undo", new UndoAction());
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        textArea.getActionMap().put("Redo", new RedoAction());
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }

    private void createFormatMenuListeners() {
        view.getWorldWrapItem().addItemListener(new WorldWrapAction());
        view.getFontItem().addActionListener(new FontChooseAction());
        view.getColorItem().addActionListener(new ColorChooseAction());
        view.getBackgroundColorItem().addActionListener(new BackgroundColorChooseAction());
    }

    private void createFileMenuListeners() {
        view.getNewItem().addActionListener(new NewFileAction());
        view.getOpenItem().addActionListener(new OpenFileAction());
        view.getSaveItem().addActionListener(new SaveFileAction());
        view.getSaveAsItem().addActionListener(new SaveAsFileAction());
        view.getExitItem().addActionListener(new ExitAction());
    }

    private void createTextAreaListeners() {
        textArea.getDocument().addDocumentListener(new TextAreaChangeAction());
    }

    public void exit() {
        new ExitAction().actionPerformed(null);
    }

    class NewFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textArea.setText("");
            file = null;
        }
    }

    class OpenFileAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int returnVal = fileChooser.showOpenDialog(view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                textArea.setText("");
                file = fileChooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    br.close();
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(view,
                            "There was a problem opening the file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

     class SaveFileAction implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
             if (file == null) {
                 new SaveAsFileAction().actionPerformed(actionEvent);
             }
             else {
                 try {
                     FileWriter fileWriter = new FileWriter(file);
                     fileWriter.write(textArea.getText());
                     fileWriter.close();
                     documentChanged = false;

                 } catch (IOException err) {
                     JOptionPane.showMessageDialog(view,
                             "There was a problem saving the file.",
                             "Error",
                             JOptionPane.ERROR_MESSAGE);
                 }
             }
         }
     }

    class SaveAsFileAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setSelectedFile(new File("untilted.txt"));
            int returnVal = fileChooser.showSaveDialog(view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(textArea.getText());
                    fileWriter.close();
                    documentChanged = false;

                } catch (IOException err) {
                    JOptionPane.showMessageDialog(view,
                            "There was a problem saving the file.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (documentChanged) {
                int result = JOptionPane.showConfirmDialog(
                        view,
                        "You have unsaved changes, close anyway?",
                        "Document changes",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    return;
                }
            }

            System.exit(0);
        }
    }

    class TextAreaChangeAction implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            documentChanged = true;
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            documentChanged = true;
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            documentChanged = true;
        }
    }

    class WorldWrapAction implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            System.out.println(view.getWorldWrapItem().isSelected());
            textArea.setLineWrap(view.getWorldWrapItem().isSelected());
        }
    }

    class FontChooseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFontChooser fontChooser = new JFontChooser();
            int result = fontChooser.showDialog(view);

            if (result == JFontChooser.OK_OPTION) {
                Font font = fontChooser.getSelectedFont();
                textArea.setFont(font);
            }
        }
    }

    class ColorChooseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Color newColor = JColorChooser.showDialog(
                    view,
                    "Choose text color",
                    Color.BLACK);

            if (newColor != null) {
                textArea.setForeground(newColor);
            }
        }
    }

    class BackgroundColorChooseAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Color newColor = JColorChooser.showDialog(
                    view,
                    "Choose background color",
                    Color.WHITE);

            if (newColor != null) {
                textArea.setBackground(newColor);
            }
        }
    }

    class PasteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            textArea.paste();
        }
    }

    class UndoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            } catch (CannotUndoException e) {
            }
        }
    }

    class RedoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            } catch (CannotRedoException e) {
            }
        }
    }

    class DisableItemsAction implements MenuListener {
        @Override
        public void menuSelected(MenuEvent menuEvent) {
            if (textArea.getSelectedText() == null) {
                view.getCopyItem().setEnabled(false);
                view.getDeleteItem().setEnabled(false);
                view.getCutItem().setEnabled(false);
            }
            else {
                view.getCopyItem().setEnabled(true);
                view.getDeleteItem().setEnabled(true);
                view.getCutItem().setEnabled(true);
            }
            view.getUndoItem().setEnabled(undoManager.canUndo());
            view.getRedoItem().setEnabled(undoManager.canRedo());
        }

        @Override
        public void menuDeselected(MenuEvent menuEvent) {

        }

        @Override
        public void menuCanceled(MenuEvent menuEvent) {

        }
    }

    class CopySelectedAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String myString = textArea.getSelectedText();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        }
    }

    class DeleteSelectedAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            textArea.replaceSelection("");
        }
    }

    class SelectAllAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            textArea.selectAll();
        }
    }

    class FindReplaceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JPanel panel = new JPanel();
            panel.setLayout(new MigLayout(
                    "",
                    "",
                    ""
            ));
            JTextField findText = new JTextField();
            JTextField replaceText = new JTextField();
            JLabel findTextLabel = new JLabel("Find text: ");
            JLabel replaceTextLabel = new JLabel("Replace with text: ");

            panel.add(findTextLabel, "span");
            panel.add(findText, "span, grow, gapbottom 10");
            panel.add(replaceTextLabel, "span");
            panel.add(replaceText, "span, grow");

            int result = JOptionPane.showConfirmDialog(view, panel, "Find and replace",JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String text = textArea.getText();
                textArea.setText(text.replaceAll( "(\\b" + findText.getText() + "\\b)", replaceText.getText()));
            }
        }
    }

    class CutSelectedAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new CopySelectedAction().actionPerformed(actionEvent);
            new DeleteSelectedAction().actionPerformed(actionEvent);
        }
    }
}
