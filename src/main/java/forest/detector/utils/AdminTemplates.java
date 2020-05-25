package forest.detector.utils;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;
import static j2html.TagCreator.button;

public class AdminTemplates {

    public static final ContainerTag HEAD = head(
            title("Admin page"),
            // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/script

            meta().attr("charset", "UTF-8"),
            meta().attr("name", "UTF-8").attr("content", "width=device-width, initial-scale=1.0"),
            meta().attr("http-equiv", "X-UA-Compatible").attr("content", "ie=edge"),
            link().withRel("stylesheet").withHref("/css/admin-page.css"),
            link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css")
                    .attr("crossorigin","anonymous"),
            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js")
                    .attr("crossorigin", "anonymous")

    );
    public static final ContainerTag MENU = nav(
            div(
                    div(
                            div("Main").withClass("sb-sidenav-menu-heading"),
                            a(
                                    div(i().withClass("fas fa-tachometer-alt"))
                                            .withClass("sb-nav-link-icon"),text("Dashboard")).withClass("nav-link").withHref("/admin"),

                            a(
                                    div(i().withClass("fas fa-user"))
                                            .withClass("sb-nav-link-icon"),text("User Management")).withClass("nav-link").withHref("/admin/user-management")

                    ).withClass("nav")
            ).withClass("sb-sidenav-menu"),
            div(
                    div("Logged in as:").withClass("small"),text("User Nickname")
            ).withClass("sb-sidenav-footer")

    ).withClass("sb-sidenav accordion sb-sidenav-dark")
            .withId("sidenavAccordion");
    public static final ContainerTag NAV = nav(
            a(text("Admin page")).withClass("navbar-brand"),button(i().withClass("fas fa-bars")).withClass("btn btn-link btn-sm order-1 order-lg-0"
            ).withId("sidebarToggle").withHref("#"),
            form().withClass("d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0"),
            ul(
                    li(a(i().withClass("fas fa-user fa-fw")).withClass("nav-link dropdown-toggle").withId("userDropdown")
                            .withHref("#").attr("role","button")
                            .attr("data-toggle","dropdown")
                            .attr("aria-haspopup","true")
                            .attr("aria-expanded","aria-expanded"),

                    div(a("Settings").withClass("dropdown-item")
                        .withHref("#"),

                            div().withClass("dropdown-divider"),
                            a("Logout").withClass("dropdown-item").withHref("/logout")
                    ).withClass("dropdown-menu dropdown-menu-right")
                            .attr("aria-labelledby","userDropdown")
                    ).withClass("nav-item dropdown")

            ).withClass("navbar-nav ml-auto ml-md-0")
    ).withClass("sb-topnav navbar navbar-expand navbar-dark bg-dark");
//source


    public static final ContainerTag FOOTER = footer(
div(
        div().withClass("d-flex align-items-center justify-content-between small")
).withClass("container-fluid"),


            script().withSrc("https://code.jquery.com/jquery-3.4.1.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("/js/scripts.js"),

            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("/js/chart-area-demo.js"),

            script().withSrc("/js/chart-bar-demo.js"),

            script().withSrc("https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("/js/datatables-demo.js")

    ).withClasses("py-4 bg-light mt-auto");
}
