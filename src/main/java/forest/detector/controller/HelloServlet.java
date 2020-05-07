package forest.detector.controller;

import forest.detector.service.HelloService;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MyServlet", urlPatterns = {"/home"})
public class HelloServlet extends HttpServlet {

    private final HelloService helloService = new HelloService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write(helloService.getGreetingMessage().getBytes());
        out.flush();
        out.close();
    }
    
}
