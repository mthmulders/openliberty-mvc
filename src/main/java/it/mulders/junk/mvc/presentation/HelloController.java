package it.mulders.junk.mvc.presentation;

import it.mulders.junk.mvc.service.GreetingService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.Controller;
import javax.mvc.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RequestScoped
@Controller
@Path("hello")
public class HelloController {
    @Inject
    Models models;

    @Inject
    GreetingService greetingService;

    @GET
    @View("hello.jsp")
    public void hello(@QueryParam("name") final String name) {
        models.put("greeting", greetingService.generateGreeting(name));
    }
}
