package it.mulders.junk.mvc.presentation;

import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;

@SessionScoped
public class UserPreferences implements Serializable {
    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
