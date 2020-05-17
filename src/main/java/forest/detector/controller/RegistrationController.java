package forest.detector.controller;

import forest.detector.entity.PasswordHashing;
import forest.detector.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private UserRepository userRepository = new UserRepository();
    private PasswordHashing hashing = new PasswordHashing();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String html = "<html><body>";

        html+=  "<center>"+
                "<form method ='post' name ='registration' action='/registration'>" +
                "  <div class='container'>" +
                "  <center>  <h1> User Registration Form</h1> </center>" +
                "  <hr>" +
                "  <label> Email </label><br>" +
                "<input type='text' name='email' placeholder= 'Email' size='15' required /><br> <br>" +
                "<label> Password: </label><br>" +
                "<input type='password' name='password' placeholder='Password' size='15' required /><br> <br>" +
                "<label> First name: </label><br>" +
                "<input type='text' name='firstName' placeholder='First name' size='15'required /><br> <br>" +
                "<label> Last name: </label><br>" +
                "<input type='text' name='lastName' placeholder='Last name' size='15'required /><br> <br>" +
                "</div>" +
                "<input type='submit' value='Confirm'>"+
                "</center>"+
                "</form>"+
                "</body></html>";

        writer.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
        PrintWriter writer = response.getWriter();

        String html = "<html><body>";

        String email = request.getParameter("email");
        String password = null;

        password = hashing.getHash(request.getParameter("password"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9+])*(\\.[A-Za-z]{2,})$")){

            userRepository.setUserInDB(email,password,firstName,lastName);
            response.sendRedirect(request.getContextPath()+ "/login");

        }else{
            html += "<center>"+
                    "<h3 style=\"color:#FF0000\";>Wrong email</h3>" +
                    "<h3 style=\"color:#FF0000\";>please try again</h3>" +
                    "<center>";
            html += "</body></html>";
            writer.println(html);
        }

    }
}
