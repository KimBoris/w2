package org.example.w2.member;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        log.info("-------------------------------");
        log.info("uid: " + uid);
        log.info("upw: " + upw);

        //JSESSION_ID가 세션 저장소에 있으면 반환, 없으면 생성
        HttpSession session = req.getSession();

        //DB에서 해당 사용자 정보를 확인해서 사용자 정보를 얻어온다.
        try {
            Optional<MemberVO> result = MemberDAO.INSTANCE.get(uid, upw);
            result.ifPresentOrElse(//memberVO가 있다는것은 정상적인 접근
                    memberVO -> {
                        session.setAttribute("uid", memberVO);
                        try {
                            resp.sendRedirect("/mypage");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    },//1. 값이 있는 경우,
                    () -> {
                        try {
                            //얘 자체가 함수라서 예외처리를 자체적으로 해줘야 한다.
                            resp.sendRedirect("/login");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });//2. 값이 없는 경우

        } catch (Exception e) {
            throw new RuntimeException();
        }

        //DB에서 해당 사용자 정보를 확인해서 사용자 정보를 얻어온다.


        session.setAttribute("uid", uid);
        ///로그인 성공하면 마이페이지로 가세요
        resp.sendRedirect("/mypage");

    }
}
