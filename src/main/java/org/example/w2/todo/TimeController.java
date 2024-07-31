package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@WebServlet(value = "/time")
@Log4j2
public class TimeController extends HttpServlet {
   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet");

        HttpSession session = req.getSession();
        log.info(session);
        log.info(session.getId());

        try
        {
            String str = TimeDAO.INSTANCE.getNow();

            req.setAttribute("time", str);

        }
        catch (){

        }
    }*/
}
