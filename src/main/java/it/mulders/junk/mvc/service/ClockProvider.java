package it.mulders.junk.mvc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import java.time.Clock;

/**
 * Provide a default implementation of {@link Clock}.
 */
@ApplicationScoped
public class ClockProvider {
    @Produces
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
