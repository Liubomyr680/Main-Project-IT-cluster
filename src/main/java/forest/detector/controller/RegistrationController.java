package forest.detector.controller;

import forest.detector.entity.User;
import forest.detector.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {
    List<User> userList = UserRepository.getInstance().getUsers();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        //List<User> userList = UserRepository.getInstance().getUsers();

        String html = "<html><body>";

        html+=  "<center>"+
                "<form method ='post' name ='registration' action='/registration'>" +
                "  <div class='container'>" +
                "  <center>  <h1> User Registration Form</h1> </center>" +
                "  <hr>" +
                "  <label> First name </label><br>" +
                "<input type='text' name='firstName' placeholder= 'First name' size='15' required /><br> <br>" +
                "<label> Second Name: </label><br>" +
                "<input type='text' name='secondName' placeholder='Second name' size='15' required /><br> <br>" +
                "<label> Username: </label><br>" +
                "<input type='text' name='username' placeholder='User name' size='15'required /><br> <br>" +
                "<label> Password: </label><br>" +
                "<input type='password' name='password' placeholder='Password' size='15'required /><br> <br>" +
                "</div>" +
                "<input type='submit' value='Confirm'>"+
                "</center>"+
                "</form>"+
                "</body></html>";

        writer.println(html);

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("secondName");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if(!firstName.equals("") && !lastName.equals("") && !userName.equals("")){
            userList.add(new User(firstName,lastName,userName,password));
            response.sendRedirect(request.getContextPath()+ "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
