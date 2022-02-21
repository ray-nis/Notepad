package app.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    JMenu fileMenu, editMenu, formatMenu, viewMenu;
    JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem;
    JCheckBoxMenuItem worldWrapItem;
    JMenuItem fontItem, colorItem, backgroundColorItem;
    JMenuItem undoItem, redoItem, pasteItem, copyItem, cutItem, deleteItem, selectAllItem;
    JMenuItem findAndReplaceItem;
    JMenu themeMenu;
    JMenuItem lightItem, jItem, darkItem, darculaItem;

    public MenuBar() {
        init();
    }

    private void init() {
        createMenu();
        createMenuItem();
    }

    private void createMenu() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        formatMenu = new JMenu("Format");
        viewMenu = new JMenu("View");

        add(fileMenu);
        add(editMenu);
        add(formatMenu);
        add(viewMenu);
    }

    private void createMenuItem() {
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createViewMenu();
    }

    private void createViewMenu() {
        themeMenu = new JMenu("Theme");
        lightItem = new JMenuItem("Light");
        jItem = new JMenuItem("Light IntelliJ");
        darkItem = new JMenuItem("Dark");
        darculaItem = new JMenuItem("Darcula");

        themeMenu.add(lightItem);
        themeMenu.add(jItem);
        themeMenu.add(darkItem);
        themeMenu.add(darculaItem);
        viewMenu.add(themeMenu);
    }

    private void createEditMenu() {
        undoItem = new JMenuItem("Undo");
        undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));

        redoItem = new JMenuItem("Redo");
        redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

        pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        cutItem = new JMenuItem("Cut");
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        deleteItem = new JMenuItem("Delete");
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));

        selectAllItem = new JMenuItem("Select all");
        selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        findAndReplaceItem = new JMenuItem("Find and replace text");

        editMenu.add(undoItem);
        editMenu.add(redoItem);
        editMenu.add(pasteItem);
        editMenu.add(copyItem);
        editMenu.add(cutItem);
        editMenu.add(deleteItem);
        editMenu.add(selectAllItem);
        editMenu.add(findAndReplaceItem);
    }

    private void createFormatMenu() {
        fileMenu.setMnemonic(KeyEvent.VK_3);

        worldWrapItem = new JCheckBoxMenuItem("World wrap", false);
        worldWrapItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

        fontItem = new JMenuItem("Font");
        colorItem = new JMenuItem("Text color");
        backgroundColorItem = new JMenuItem("Background color");

        formatMenu.add(worldWrapItem);
        formatMenu.add(fontItem);
        formatMenu.add(colorItem);
        formatMenu.add(backgroundColorItem);
    }

    private void createFileMenu() {
        fileMenu.setMnemonic(KeyEvent.VK_1);

        newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        saveAsItem = new JMenuItem("Save as");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(exitItem);
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenu getEditMenu() {
        return editMenu;
    }

    public JMenuItem getNewItem() {
        return newItem;
    }

    public JMenuItem getOpenItem() {
        return openItem;
    }

    public JMenuItem getSaveItem() {
        return saveItem;
    }

    public JMenuItem getSaveAsItem() {
        return saveAsItem;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getFontItem() {
        return fontItem;
    }

    public JCheckBoxMenuItem getWorldWrapItem() {
        return worldWrapItem;
    }

    public JMenuItem getColorItem() {
        return colorItem;
    }

    public JMenuItem getBackgroundColorItem() {
        return backgroundColorItem;
    }

    public JMenuItem getUndoItem() {
        return undoItem;
    }

    public JMenuItem getRedoItem() {
        return redoItem;
    }

    public JMenuItem getPasteItem() {
        return pasteItem;
    }

    public JMenuItem getCopyItem() {
        return copyItem;
    }

    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    public JMenuItem getSelectAllItem() {
        return selectAllItem;
    }

    public JMenuItem getFindAndReplaceItem() {
        return findAndReplaceItem;
    }

    public JMenuItem getCutItem() {
        return cutItem;
    }

    public JMenuItem getLightItem() {
        return lightItem;
    }

    public JMenuItem getjItem() {
        return jItem;
    }

    public JMenuItem getDarkItem() {
        return darkItem;
    }

    public JMenuItem getDarculaItem() {
        return darculaItem;
    }
}
