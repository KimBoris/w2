package org.example.w2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.log4j.Log4j2;

@WebServlet(name = "helloServlet", value = "/hello")//여기 hello에는 확장자가 없다. gseshop 확장자 씀
@Log4j2
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // JSP파일을 WEB_INF밑으로 준다. 브라우저가 접근 불가
        // MVC 사상에 맞다.
        String msg = request.getParameter("msg");

        log.info("msg" + msg);

        Integer.parseInt(msg);



        request.setAttribute("msg", msg);

        try {
            request.getRequestDispatcher("/WEB-INF/hello2.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}