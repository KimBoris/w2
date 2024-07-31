package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.StringUtil;
import org.example.w2.dao.TodoDAO;

import java.io.IOException;

@WebServlet(value = "/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet {
    //얘는 포스트방식이긴하다


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doPost");

        String tnoStr = req.getParameter("tno");

        //없으면 -1
        Integer tno = StringUtil.getInt(tnoStr, -1);

        //Post는 리다이렉션 필요함 > 일단 list화면으로 보내기로했음

        boolean result = false;
        //리다이렉트할때 GET방식이라서 아래처럼 파라미터를 준다.
        try {
            result = TodoDAO.INSTANCE.delete(tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/todo/list?result=" + result);


    }
}
