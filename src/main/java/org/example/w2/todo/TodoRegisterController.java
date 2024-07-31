package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.dao.TodoDAO;

import java.io.IOException;
@Log4j2
@WebServlet(value="/todo/register")
public class TodoRegisterController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
        log.info("doGet-register");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doPost-register");
        // step1 : 브라우저에서 전달하는 데이터 수집
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");

        log.info(title, writer);

        //이 단계에서 문제가 있다면?
        //title이나 writer 등이 공백이다.> 에러를 발생시켜야 한다.
        //예외사항에 대한 처리가 필요

        // step2 : 데이터 가공해서 VO, DTO 객체 생성
        TodoVO vo = TodoVO.builder()
                .title(title)
                .writer(writer)
                .build();


        //step 3 - Service, DAO에게 처리를 부탁
        //저족에 문제가 있으면 메세지를 남겨주는것도 필요
        try {
            Integer tno = TodoDAO.INSTANCE.insert(vo);
            // step 4- 결과 전송
            resp.sendRedirect("/todo/list?tno="+tno);

        }
        catch (Exception e)
        {
            //등록했을때 문제가 있었던거라 /todo/register로 가는것이 맞고 에러메세지를 담아서 보내야 한다.
            //두가지 선택지
            //1. forward를 이용하는 경우
            //req.setAttribute("ex", e); Exception을 담는다 > JSP 페이지로 보냄
            //req.getRequestDispatcher("WEB-INF/todo/register.jsp).forward...

            //2. Redirect(GET방식)
            resp.sendRedirect("/todo/register?error=input");


           /*throw new  RuntimeException();*/
        }
    }
}
