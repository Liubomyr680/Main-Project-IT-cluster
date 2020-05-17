package forest.detector.controller;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.*;
import static forest.detector.utils.HTMLTemplates.*;

@WebServlet(name = "home", urlPatterns = "/template", loadOnStartup = 1)
public class TemplateController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Welcome page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV,
                        header(
                                div(
                                        div(
                                                div(
                                                        div(
                                                                h1(text("Build "), strong("social profiles"), text(" and gain revenue "), strong("profits")).withClass("display-4"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex")
                                                        ).withClasses("col-lg-8", "d-none", "d-lg-block"),
                                                        div(
                                                                div(
                                                                        div(
                                                                                h3("Sign Up Today"),
                                                                                p("Please fill out this form to register"),
                                                                                form(
                                                                                        div(input().withType("text").withClasses("form-control form-control-lg").withPlaceholder("Username")).withClass("form-group"),
                                                                                        div(input().withType("email").withClasses("form-control form-control-lg").withPlaceholder("Email")).withClass("form-group"),
                                                                                        div(input().withType("password").withClasses("form-control form-control-lg").withPlaceholder("Password")).withClass("form-group"),
                                                                                        div(input().withType("password").withClasses("form-control form-control-lg").withPlaceholder("Confirm Password")).withClass("form-group")
                                                                                )
                                                                        ).withClass("card-body")
                                                                ).withClasses("card bg-primary text-center card-form")
                                                        ).withClass("col-lg-4")
                                                ).withClass("row")
                                        ).withClasses("home-inner", "container")
                                ).withClass("dark-overlay")
                        ).withId("home-section"),
                        FOOTER
                )
        );
        response.getWriter().println(homeHtml.render());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{


    }
}