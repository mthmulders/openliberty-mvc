package it.mulders.junk.mvc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
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
