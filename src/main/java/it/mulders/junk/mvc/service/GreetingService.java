package it.mulders.junk.mvc.service;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static java.time.format.FormatStyle.FULL;

@ApplicationScoped
public class GreetingService {
    @Inject
    Clock clock;

    private String currentDateTime(final Locale locale) {
        final ZonedDateTime now = ZonedDateTime.now(clock);
        return DateTimeFormatter.ofLocalizedDateTime(FULL)
                .withLocale(locale)
                .format(now);
    }

    private String defaultName(final String input) {
        if (input == null || input.length() == 0) {
            return "world";
        } else {
            return input;
        }
    }

    public LocalisedGreeting generateGreeting(final String name) {
        return generateGreeting(Locale.US, name);
    }

    public LocalisedGreeting generateGreeting(final Locale locale, final String name) {
        return new LocalisedGreeting(currentDateTime(locale), defaultName(name));
    }
}
