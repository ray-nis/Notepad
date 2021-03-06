package app;

import app.controllers.PreferencesController;
import app.views.MenuBar;
import app.controllers.MenuBarController;
import com.formdev.flatlaf.FlatLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainView extends JFrame {
    JTextArea textArea;
    MenuBar menuBar;
    MenuBarController menuBarController;
    PreferencesController preferencesController;

    public MainView() {
        preferencesController = new PreferencesController();
        FlatLaf.setup(preferencesController.getLook());

        setTitle("Notepad");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon.png")));

        init();

        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                menuBarController.exit();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {
        createTextArea();
        createMenuBar();
    }

    private void createMenuBar() {
        menuBar = new MenuBar();
        menuBarController = new MenuBarController(menuBar, textArea, preferencesController);
        setJMenuBar(menuBar);
    }

    private void createTextArea() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        add(scrollPane);
    }


}
