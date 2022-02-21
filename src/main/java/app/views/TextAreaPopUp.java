package app.views;

import javax.swing.*;

public class TextAreaPopUp extends JPopupMenu {
    JMenuItem  saveItem, pasteItem, copyItem, cutItem, deleteItem, selectAllItem;

    public TextAreaPopUp() {
        init();
    }

    private void init() {
        saveItem = new JMenuItem("Save");

        pasteItem = new JMenuItem("Paste");

        copyItem = new JMenuItem("Copy");

        cutItem = new JMenuItem("Cut");

        deleteItem = new JMenuItem("Delete");

        selectAllItem = new JMenuItem("Select all");

        add(saveItem);
        add(pasteItem);
        add(copyItem);
        add(cutItem);
        add(deleteItem);
        add(selectAllItem);
    }

    public JMenuItem getSaveItem() {
        return saveItem;
    }

    public JMenuItem getPasteItem() {
        return pasteItem;
    }

    public JMenuItem getCopyItem() {
        return copyItem;
    }

    public JMenuItem getCutItem() {
        return cutItem;
    }

    public JMenuItem getDeleteItem() {
        return deleteItem;
    }

    public JMenuItem getSelectAllItem() {
        return selectAllItem;
    }
}
