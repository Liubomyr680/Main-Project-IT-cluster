package forest.detector.controller;

import forest.detector.entity.User;
import forest.detector.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="admin", urlPatterns = "/admin")
public class AdminController extends HttpServlet {

    //private UserRepository userRepository;
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String html = "<html><body>";
        HttpSession session = request.getSession(false);


        if(session != null){
            html += "<fieldset>\n" +
                    "<form method=\"post\" action=\"admin\">\n" +
                    "<label>Email for list:</label><br/>\n" +
                    "<input type=\"text\" name=\"email\" size=\"30\"><br/>\n" +
                    "<label>Choose role for User:</label>\n" +
                    "\n" +
                    "<select name=\"role\">\n" +
                    "  <option value=\"admin\">Admin</option>\n" +
                    "  <option value=\"moderator-api\">Moderator-API</option>\n" +
                    "  <option value=\"moderator-gui\">Moderator-GUI</option>\n" +
                    "</select>"+
                    "<input type=\"submit\" value=\"Enter\"><br/>\n" +
                    "<input type=\"submit\" value=\"Delete User\"><br/>\n" +
                    "</form>\n" +
                    "<form method='get' action='/logout'>" +
                    "<input type='submit' value='Logout'>" +
                    "</form>"+
                    "</fieldset>\n" +
                    "</body></html>";
        }else{
            response.sendRedirect("/login");
        }

        writer.println(html);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        String email = request.getParameter("email");
        String role = request.getParameter("role");

        if(email != null && role != null){
            User user = userService.getUserByEmail(email);
            if(user != null) {
                userService.updateUserRoleInDB(role, email);
                userService.deleteUser(email);
                response.sendRedirect("/admin");
            }
        }
    }
}
