package it.mulders.junk.mvc.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import static java.time.format.FormatStyle.FULL;

@ApplicationScoped
@Named("timeService")
public class TimeService {
    public String currentDateTime() {
        return DateTimeFormatter.ofLocalizedDateTime(FULL)
                .format(LocalDateTime.now());
    }
}
