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

@WebServlet(name ="admin", urlPatterns = "/admin")
public class AdminController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String html = "<html><body>";

        html+=  "<center>"+
                "<form method ='post' name ='admin' action='/admin'>" +
                "  <div class='container'>" +
                "  <center>  <h1> User Registration Form</h1> </center>" +
                "  <hr>" +
                "  <label> User Email </label><br>" +
                "<input type='text' name='email' placeholder= 'Email' size='15' required /><br> <br>" +
                "<input type='submit' value='Find'>"+
                "</center>"+
                "</form>"+
                "</body></html>";

        writer.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //doGet(request,response);

        PrintWriter writer = response.getWriter();
        UserRepository userRepository = new UserRepository();

        //String role = "";
        String email = request.getParameter("email");

        String html = "<html><body>";

        if(email != null){
            User user = userRepository.getUserByEmail(email);
            if(user != null){
                html+=  "<center>"+
                        "<form name='select' method='get' action ='/select'>" +
                        "<select name='select'>" +
                        "<option value='one'>one</option>" +
                        "<option value='two'>two</option>" +
                        "<input type='submit' value='submit'>" +
                        "</center>"+
                        "</form>"+
                        "</body></html>";
                String role = request.getParameter("email");
                writer.println(role);

            } else {



//                userRepository.updateUserRoleInDB(role,email);

                response.sendRedirect("/admin");
            }

        }



        writer.println(html);
    }
}
