package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(value = "/mypage")
@Log4j2
public class MyPageController extends HttpServlet {
    //REDIRECT = GET방식이기떄문에
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet");

        //HTTP세션을 얻어왔는데 리모컨이없으면 ? 로그인을 한적이 없는 사용자
        //mypage를 무작정 방문할 수도 있는데 쿠키 다 지우고 무작정 방문할 수도 있다 > 로그인을 안한 사용자

        //기존에 뭔가 없었으면 새로 만들지마세요
        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("uid")==null)
        {
            resp.sendRedirect("/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/mypage.jsp").forward(req, resp);

    }
}
