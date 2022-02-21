package app.controllers;

import com.formdev.flatlaf.*;

import java.util.prefs.Preferences;

public class PreferencesController {
    final String LIGHT = "Light";
    final String LIGHT_J = "Light IntelliJ";
    final String DARK = "Dark";
    final String DARCULA = "Darcula";
    final String THEME_PREFERENCE = "theme_preference";

    Preferences prefs;

    public PreferencesController() {
        prefs = Preferences.userNodeForPackage(app.MainView.class);
    }

    public FlatLaf getLook() {
        String look = prefs.get(THEME_PREFERENCE, LIGHT);
        switch (look) {
            case LIGHT_J:
                return new FlatIntelliJLaf();
            case DARK:
                return new FlatDarkLaf();
            case DARCULA:
                return new FlatDarculaLaf();
            default:
                return new FlatLightLaf();
        }
    }

    public void setLook(String look) {
        prefs.put(THEME_PREFERENCE, look);
    }
}
