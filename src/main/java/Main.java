import app.MainView;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
             MainView mv = new MainView();
        });
    }
}
