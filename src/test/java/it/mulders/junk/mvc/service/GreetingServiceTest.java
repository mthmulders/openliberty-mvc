package it.mulders.junk.mvc.service;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GreetingServiceTest {
    private static final Instant INSTANT = Instant.parse("2007-12-03T10:15:30.00Z");
    private static final ZoneId ZONE = ZoneId.of("Europe/Amsterdam");

    private final GreetingService service = new GreetingService();

    @Before
    public void injectClock() {
        service.clock = Clock.fixed(INSTANT, ZONE);
    }

    @Test
    public void generateGreeting_shouldCopyName() {
        final LocalisedGreeting greeting = service.generateGreeting("John Doe");
        assertThat(greeting.getName(), is("John Doe"));
    }

    @Test
    public void generateGreeting_withNullName_shouldUseDefaultName() {
        final LocalisedGreeting greeting = service.generateGreeting(null);
        assertThat(greeting.getName(), is("world"));
    }

    @Test
    public void generateGreeting_withEmptyName_shouldUseDefaultName() {
        final LocalisedGreeting greeting = service.generateGreeting("");
        assertThat(greeting.getName(), is("world"));
    }

    @Test
    public void generateGreeting_shouldUseEnglish() {
        final LocalisedGreeting greeting = service.generateGreeting("");
        assertThat(greeting.getCurrentDateTime(), is("Monday, December 3, 2007 11:15:30 AM CET"));
    }

    @Test
    public void generateGreeting_withDifferentLocale_shouldUseLocale() {
        final LocalisedGreeting greeting = service.generateGreeting(Locale.forLanguageTag("nl-NL"), "");
        assertThat(greeting.getCurrentDateTime(), is("maandag 3 december 2007 11:15:30 uur CET"));
    }
}