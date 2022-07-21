package it.mulders.junk.mvc.presentation;

import it.mulders.junk.mvc.service.GreetingService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.mvc.Controller;
import jakarta.mvc.View;
import jakarta.mvc.security.CsrfProtected;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.QueryParam;
import java.util.Locale;

@RequestScoped
@Controller
@Path("hello")
public class HelloController {
    @Inject
    UserPreferences preferences;

    @Inject
    Models models;

    @Inject
    GreetingService greetingService;

    @GET
    @Path("/start")
    @View("input.jsp")
    public void start() {
    }

    private Locale detectLocale(final String input) {
        if (input != null && input.length() > 0) {
            return Locale.forLanguageTag(input);
        } else {
            return Locale.US;
        }
    }

    @CsrfProtected
    @POST
    @Path("/configure")
    @View("redirect:/hello")
    public void configure(@FormParam("locale") final String locale) {
        final Locale result = detectLocale(locale);
        preferences.setLocale(result);
    }

    @GET
    @View("hello.jsp")
    public void hello(@QueryParam("name") final String name) {
        final Locale locale = preferences.getLocale();
        models.put("greeting", greetingService.generateGreeting(locale, name));
    }
}
