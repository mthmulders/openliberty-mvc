package it.mulders.junk.mvc.service;

public class LocalisedGreeting {
    private final String currentDateTime;
    private final String name;

    public LocalisedGreeting(String currentDateTime, String name) {
        this.currentDateTime = currentDateTime;
        this.name = name;
    }

    public String getCurrentDateTime() {
        return currentDateTime;
    }

    public String getName() {
        return name;
    }
}
