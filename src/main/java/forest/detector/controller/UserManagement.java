package forest.detector.controller;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static forest.detector.utils.AdminTemplates.*;
import static forest.detector.utils.AdminTemplates.FOOTER;
import static j2html.TagCreator.*;
import static j2html.TagCreator.th;
@WebServlet(name = "users", urlPatterns = "/admin/user-management", loadOnStartup = 1)
public class UserManagement extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited User Management page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(
                title("User Management"),
                link().withHref("/css/user-management.css").withRel("stylesheet"),
                HEAD,
                body(
                        NAV,
                        div(

                                div(

                                        MENU

                                ).withId("layoutSidenav_nav"),
                                div(
                                        main(
                                                div(                  div(
                                                div(
                                                        div(
                                                                div(
                                                                        div(
                                                                                div(
                                                                                        div(
                                                                                                h2(text("User "),b("Management"))
                                                                                        ).withClass("col-sm-5"),
                                                                                        div(
                                                                                                a(
                                                                                                        i("\uE147").withClass("material-icons"),
                                                                                                        span("Add New User")
                                                                                                ).withHref("#").withClass("btn btn-primary")
                                                                                        ).withClass("col-sm-7")

                                                                                ).withClass("row")
                                                                        ).withClass("table-title"),
                                                                        div(
                                                                                div(
                                                                                        table(
                                                                                            thead(
                                                                                                    tr(
                                                                                                            th("#"),
                                                                                                            th("Name"),
                                                                                                            th("Date Created"),
                                                                                                            th("Role"),
                                                                                                            th("Status"),
                                                                                                            th("Action")
                                                                                                    )
                                                                                            ),
                                                                                                tfoot(
                                                                                                        th("#"),
                                                                                                        th("Name"),
                                                                                                        th("Date Created"),
                                                                                                        th("Role"),
                                                                                                        th("Status"),
                                                                                                        th("Action")
                                                                                                ),
                                                                                                tbody(
                                                                                                        tr(
                                                                                                                td("1"),
                                                                                                                td("Did pixto"),
                                                                                                                td("22/05/2020"),
                                                                                                                td("Admin"),
                                                                                                                td("Online"),
                                                                                                                td(
                                                                                                                        a(i("\uE8B8").withClass("material-icons"))
                                                                                                                                .withHref("#")
                                                                                                                                .withClass("settings"),
                                                                                                                        a(i("\uE5C9").withClass("material-icons"))
                                                                                                                                .withHref("#")
                                                                                                                                .withClass("delete")

                                                                                                                )
                                                                                                        )
                                                                                                )


                                                                                        ).withClass("table table-bordered")
                                                                                                .withId("dataTable")
                                                                                                .attr("width","100%")
                                                                                                .attr("cellspacing","0")
                                                                                ).withClass("table-responsive")
                                                                        ).withClass("card-body")
                                                                ).withClass("table-wrapper")
                                                        ).withClass("container")

                                                        ).withClass("card mb-4")

                                                ).withClass("container-fluid")
                                )

                                        ),  FOOTER

                                ).withId("layoutSidenav_content")


                        ).withId("layoutSidenav")


                ).withClass("sb-nav-fixed")
        );
        response.getWriter().println(homeHtml.render());
    }
}
