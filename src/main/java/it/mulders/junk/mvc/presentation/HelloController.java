package it.mulders.junk.mvc.presentation;

import it.mulders.junk.mvc.service.GreetingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.Controller;
import javax.mvc.View;
import javax.mvc.security.CsrfProtected;
import javax.ws.rs.GET;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
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
