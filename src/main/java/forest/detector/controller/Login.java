package forest.detector.controller;
import forest.detector.entity.User;
import forest.detector.service.UserService;
import forest.detector.utils.PasswordHashing;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

import static forest.detector.utils.AdminTemplates.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.div;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);
    private PasswordHashing hashing = new PasswordHashing();
    private UserService userService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Visited Login page!");
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role == null) {

            ContainerTag homeHtml = html(
                    title("Login"),
                    HEAD,
                    body(

                            div(
                                    div(
                                            main(
                                                    div(
                                                            div(
                                                                    div(
                                                                            div(
                                                                                    div(
                                                                                            h3("Login").withClass("text-center font-weight-light my-4")
                                                                                    ).withClass("card-header"),
                                                                                    div(
                                                                                            form().withMethod("post").with(
                                                                                                    div(
                                                                                                            label("Email").withClass("small mb-1")
                                                                                                                    .attr("for", "inputEmailAddress"),
                                                                                                            input().withClass("form-control py-4")
                                                                                                                    .withId("inputEmailAddress")
                                                                                                                    .withName("email")

                                                                                                                    .withPlaceholder("Enter email address")
                                                                                                                    .withType("email")
                                                                                                    ).withClass("form-group"),

                                                                                                    div(
                                                                                                            label("Password").withClass("small mb-1")
                                                                                                                    .attr("for", "inputPassword"),
                                                                                                            input().withClass("form-control py-4")
                                                                                                                    .withId("inputPassword")
                                                                                                                    .withName("password")

                                                                                                                    .withPlaceholder("Enter password")
                                                                                                                    .withType("password")
                                                                                                    ).withClass("form-group"),
                                                                                                    div(
//                                                                             a("Forgot Password?").withClass("small")
//                                                                                .withHref("")
                                                                                                            button("Login").withClass("btn btn-primary btn-block").withType("submit")
                                                                                                    ).withClass("form-group d-flex align-items-center justify-content-between mt-4 mb-0 center-a")
                                                                                            )
                                                                                    ).withClass("card-body"),
                                                                                    div(
                                                                                            div(
                                                                                                    a("Need an account? Sign up!").withHref("/register")
                                                                                            ).withClass("small")
                                                                                    ).withClass("card-footer text-center")
                                                                            ).withClass("card shadow-lg border-0 rounded-lg mt-5")
                                                                    ).withClass("col-lg-5")
                                                            ).withClass("row justify-content-center")
                                                    ).withClass("container")
                                            )
                                    ).withId("layoutAuthentication_content")
                            ).withId("layoutAuthentication"),
                            script().withSrc("https://code.jquery.com/jquery-3.4.1.min.js")
                                    .attr("crossorigin", "anonymous"),
                            script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js")
                                    .attr("crossorigin", "anonymous")
                    ).withClass("bg-primary")
            );
            response.getWriter().println(homeHtml.render());
        }
        else if(role.equals("admin"))
            response.sendRedirect("/admin");
        else if(role.equals("user"))
            response.sendRedirect("/template");


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();


        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        String email = request.getParameter("email");
        String password = hashing.getHash(request.getParameter("password"));

        if(email != null && password != null){

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            HttpSession session = request.getSession();

            try
            {
                String userValidate = userService.authenticateUser(user.getEmail(), user.getPassword());

                if(userValidate.equals("Admin_Role"))
                {
                    // HttpSession session = request.getSession();
                    session.setAttribute("email", email); //setting session attribute
                    String str = userService.getUserByEmail(email).getRole();
                    session.setAttribute("role", str);
                    //request.setAttribute("email", email);
                    response.sendRedirect("/admin");
                }
                else if(userValidate.equals("Moderator-api"))
                {
//                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
                    // request.setAttribute("email", email);
                    response.sendRedirect("/template");
                }
                else if(userValidate.equals("Moderator-gui"))
                {
//                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
                    // request.setAttribute("email", email);
                    response.sendRedirect("/template");
                }
                else if(userValidate.equals("User"))
                {
//                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("email", email);

                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
                    //request.setAttribute("email", email);
                    response.sendRedirect("/template");
                }
                else
                {
                    writer.println("Error message = "+userValidate);
                    request.setAttribute("errMessage", userValidate);
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }


}


