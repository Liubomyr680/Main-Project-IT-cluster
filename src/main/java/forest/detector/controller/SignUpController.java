package forest.detector.controller;


import forest.detector.entity.User;
import j2html.tags.ContainerTag;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static j2html.TagCreator.*;

@WebServlet(name ="login", urlPatterns = "/login")
public class SignUpController extends HttpServlet {

    RegistrationController registrationController = new RegistrationController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{


        PrintWriter writer = response.getWriter();

        String html = "<html><body>";

        html+=  "<form action='/login'>"+
                "<center>"+
                "<h1>Enter login and password</h1>"+
                "<div class ='input-form'>"+
                "<input type='text' name ='login' placeholder='Login'>"+
                "</div>"+
                "<div class ='input-form'>"+
                "<input type='password' name ='password' placeholder='Password'>"+
                "</div>"+
                "<div class ='input-form'>"+
                "<input type='submit' value='Enter'><br><br>"+
                "</div>"+
                "<a class='href' href='/registration'>registration</a>"+
                "</center>"+
                "</form></body></html>";

        writer.println(html);

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        for(User finder : registrationController.userList){
            if(login.equals(finder.getUserName()) && password.equals(finder.getPassword()))
                response.sendRedirect(request.getContextPath()+ "/template");
        }
        //нижче має іти перевірка з базою даних користувачів



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        doGet(request,response);






    }
}
