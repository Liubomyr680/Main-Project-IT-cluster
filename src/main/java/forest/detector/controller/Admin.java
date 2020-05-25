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
import static j2html.TagCreator.*;
@WebServlet(name = "admin", urlPatterns = "/admin", loadOnStartup = 1)
public class Admin extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Admin page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV,
                        div(
                                div(

                                MENU

                                ).withId("layoutSidenav_nav"),
                                div(
                                        main(
                                                div(
                                                        h1("Dashboard").withClass("mt-4"),
                                                        div(
                                                                div(
                                                                        div(
                                                                               div(
                                                                                       i().withClass("fas fa-chart-area mr-1"),
                                                                               text("Area Chart Example")).withClass("card-header"),
                                                                                div(
                                                                                        canvas().withId("myAreaChart")
                                                                                                .attr("width","100%")
                                                                                                .attr("height","40")

                                                                                ).withClass("card-body")

                                                                        ).withClass("card mb-4")
                                                                ).withClass("col-xl-6"),
                                                                div(
                                                                        div(
                                                                                div(
                                                                                        i().withClass("fas fa-chart-bar mr-1"),
                                                                                                text("Bar Chart Example")).withClass("card-header"),
                                                                                div(
                                                                                        canvas().withId("myBarChart")
                                                                                                .attr("width","100%")
                                                                                                .attr("height","40")

                                                                                ).withClass("card-body")

                                                                        ).withClass("card mb-4")
                                                                ).withClass("col-xl-6")
                                                        ).withClass("row"),
                                                        div(
                                                                div(i().withClass("fas fa-table mr-1"),text("DataTable Example")
                                                                ).withClass("card-header"),
                                                                div(
                                                                        div(

                                                                                table(
                                                                                        thead(
                                                                                                tr(
                                                                                                        th("Number"),
                                                                                                        th("Region"),
                                                                                                        th("Forest user"),
                                                                                                        th("Start date"),
                                                                                                        th("Finish date"),
                                                                                                        th("Forestry"),
                                                                                                        th("Cutting type"),
                                                                                                        th("Ticket status"),
                                                                                                        th("Cutting status")
                                                                                                )

                                                                                        ),
                                                                                        tfoot(
                                                                                                tr(
                                                                                                        th("Number"),
                                                                                                        th("Region"),
                                                                                                        th("Forest user"),
                                                                                                        th("Start date"),
                                                                                                        th("Finish date"),
                                                                                                        th("Forestry"),
                                                                                                        th("Cutting type"),
                                                                                                        th("Ticket status"),
                                                                                                        th("Cutting status")
                                                                                                )

                                                                                        ),
                                                                                        tbody(
                                                                                                tr(
                                                                                                        th("45645645"),
                                                                                                        th("IF"),
                                                                                                        th("Admin"),
                                                                                                        th("22.05.2020"),
                                                                                                        th("22.05.2020"),
                                                                                                        th("N/A"),
                                                                                                        th("N/A"),
                                                                                                        th("Ok"),
                                                                                                        th("Ok")
                                                                                                ),
                                                                                                tr(
                                                                                                        th("45645645"),
                                                                                                        th("IF"),
                                                                                                        th("Admin"),
                                                                                                        th("22.05.2020"),
                                                                                                        th("22.05.2020"),
                                                                                                        th("N/A"),
                                                                                                        th("N/A"),
                                                                                                        th("Ok"),
                                                                                                        th("Ok")
                                                                                                )
                                                                                        )


                                                                                ).withClass("table table-bordered")
                                                                                        .withId("dataTable").attr("width","100%")
                                                                                .attr("cellspacing","0")

                                                                        ).withClass("table-responsive")

                                                                ).withClass("card-body")

                                                        ).withClass("card mb-4")

                                                ).withClass("container-fluid")

                                        ),  FOOTER

                                ).withId("layoutSidenav_content")


                        ).withId("layoutSidenav")

                ).withClass("sb-nav-fixed")
        );
        response.getWriter().println(homeHtml.render());
    }
}
