package it.mulders.junk.mvc.presentation;

import it.mulders.junk.mvc.service.TimeService;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Controller
@Path("hello")
public class HelloController {
    @Inject
    Models models;

    @Inject
    TimeService timeService;

    @GET
    @View("hello.jsp")
    public void hello() {
        models.put("currentDateTime", timeService.currentDateTime());
    }
}
