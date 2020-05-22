package forest.detector.controller;


import forest.detector.service.UserService;
import forest.detector.utils.PasswordHashing;
import forest.detector.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name ="login", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private PasswordHashing hashing = new PasswordHashing();
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession(false);
//        String role = (String) session.getAttribute("role");

        String html = "<html><body>";

        if(session == null){
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

        }else{

            html +="<h1>HELLO"+ session.getAttribute("role") +"</h1>" +
                    "</body></html>";

        }
//        else{
////            html += "<form method='get' action='/logout'>" +
////                    "<input type='submit' value='Logout'>" +
////                    "</form></body></html>";
//
//            if(role == "admin"){
//                response.sendRedirect(request.getContextPath()+ "/admin");
//            } else if(role == "user"){
//                html+="HELLO USER" +
//                        "</body></html>";
//            }
//
//        }

        writer.println(html);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();

        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        String email = request.getParameter("email");
        String password = hashing.getHash(request.getParameter("password"));

        if(email != null && password != null){

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            try
            {
                String userValidate = userService.authenticateUser(user.getEmail(), user.getPassword());

                if(userValidate.equals("Admin_Role"))
                {
                    //writer.println("Admin's Home");   //here must be admin page

                     //Creating a session
                    session.setAttribute("email", email); //setting session attribute
                    session.setAttribute("role", userService.getUserByEmail(email).getRole());
                    request.setAttribute("email", email);

                    response.sendRedirect("/admin");
                }
                else if(userValidate.equals("Moderator-api"))
                {
                    writer.println("Moderator-api");  //here must be Moderator-api page

//                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
                    request.setAttribute("email", email);
                }
                else if(userValidate.equals("Moderator-gui"))
                {
                    writer.println("Moderator-gui");   //here must be Moderator-gui page

//                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);
                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
                    request.setAttribute("email", email);
                }
                else if(userValidate.equals("User"))
                {
                    writer.println("User");   //here must be user page

//                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("email", email);
                    session.setAttribute("role",  userService.getUserByEmail(email).getRole());
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
