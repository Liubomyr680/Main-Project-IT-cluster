package forest.detector.controller;


import forest.detector.entity.PasswordHashing;
import forest.detector.entity.User;
import forest.detector.repository.UserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name ="login", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private PasswordHashing hashing = new PasswordHashing();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter writer = response.getWriter();
        String html = "<html><body>";

        html+=  "<form method ='post' action='/login'>"+
                "<center>"+
                "<h1>Enter login and password</h1>"+
                "<div class ='input-form'>"+
                "<input type='text' name ='email' placeholder='Email'>"+
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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        doGet(request,response);
        PrintWriter writer = response.getWriter();
        UserRepository userRepository = new UserRepository();

        String email = request.getParameter("email");
        String password = null;
        password = hashing.getHash(request.getParameter("password"));

        String html = "<html><body>";

        if(email != null && password != null){

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            try
            {
                String userValidate = userRepository.authenticateUser(user.getEmail(), user.getPassword());

                if(userValidate.equals("Admin_Role"))
                {
                    //writer.println("Admin's Home");   //here must be admin page

                    HttpSession session = request.getSession(); //Creating a session
                    session.setAttribute("Admin", email); //setting session attribute
                    request.setAttribute("email", email);

                    response.sendRedirect("/admin");
                }
                else if(userValidate.equals("Editor_Role"))
                {
                    writer.println("Editor's Home");  //here must be editor page

                    HttpSession session = request.getSession();
                    session.setAttribute("Editor", email);
                    request.setAttribute("email", email);
                }
                else if(userValidate.equals("User_Role"))
                {
                    writer.println("User's Home");   //here must be user page

                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("User", email);
                    request.setAttribute("email", email);
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
