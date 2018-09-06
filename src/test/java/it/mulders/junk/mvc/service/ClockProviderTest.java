package it.mulders.junk.mvc.service;

import org.junit.Test;

import java.time.Clock;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ClockProviderTest {
    private ClockProvider provider = new ClockProvider();

    @Test
    public void clock_shouldNotProvideFixedClock() {
        final Clock clock = provider.clock();
        final String implementation = clock.getClass().getName();
        assertThat(implementation, not(containsString("Fixed")));
    }
}