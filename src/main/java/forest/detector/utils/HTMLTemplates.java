package forest.detector.utils;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;

public class HTMLTemplates {

    public static final ContainerTag HEAD = head(
            title("Welcome Java 9 Group"),
            meta().attr("charset", "UTF-8"),
            meta().attr("name", "UTF-8").attr("content", "width=device-width, initial-scale=1.0"),
            meta().attr("http-equiv", "X-UA-Compatible").attr("content", "ie=edge"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://use.fontawesome.com/releases/v5.13.0/css/all.css"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css")
                    .attr("integrity", "sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh")
                    .attr("crossorigin", "anonymous"),
            link()
                    .withRel("stylesheet")
                    .withHref("/css/style.css"),
            script().withSrc("http://code.jquery.com/jquery-3.5.0.min.js")
                    .attr("integrity", "sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js")
                    .attr("integrity", "sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js")
                    .attr("integrity", "sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6")
                    .attr("crossorigin", "anonymous")
    );

    public static final ContainerTag NAV = nav(
        div(
                a("LoopLAB").withHref("/").withClass("navbar-brand"),
                button(
                        span().withClass("navbar-toggler-icon")
                ).withClass("navbar-toggler").attr("data-toggle", "collapse").attr("data-target", "#navbarCollapse"),
                div(
                        ul(
                                li(a("Home").withHref("#home").withClass("nav-link")),
                                li(a("Explore").withHref("#explore-head-section").withClass("nav-link")),
                                li(a("Create").withHref("#create-head-section").withClass("nav-link")),
                                li(a("Share").withHref("#share-head-section").withClass("nav-link"))
                        ).withClasses("navbar-nav", "ml-auto")
                ).withClasses("collapse", "navbar-collapse").withId("navbarCollapse")
        ).withClasses("container")
    ).withClasses("navbar", "navbar-expand-sm", "bg-dark navbar-dark", "fixed-top");

    public static final ContainerTag FOOTER = footer(
            div(
                    div(
                            div(
                                h3("LoopLAB"),
                                p("Copyright @").with(span().withId("year")),
                                button("Contact Us").withClasses("btn", "btn-primary")
                                        .attr("data-toggle", "modal")
                                        .attr("data-target", "#contactModal")
                            ).withClasses("col", "text-center", "py-4")
                    ).withClasses("row")
            ).withClasses("container")
    ).withId("main-footer").withClasses("bg-dark");
}
