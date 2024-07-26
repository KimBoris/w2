package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.bmi.Scores;
import org.example.w2.common.PageInfo;
import org.example.w2.common.StringUtil;
import org.example.w2.dao.TodoDAO;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@WebServlet(value = "/todo/list") //얘는 todo의 list라는 경로다.
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("Todo List Get");

        /* // String tag ="<script src='49.174.76.109/sample.js'></script>";



*//*
        Scores scores = Scores.builder()
                .subject1(10)
                .subject2(20).subject3(30).subject4(40).subject5(50).build();
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
*//*
        //객체형으로 담아서 만든다.
        List<Integer> nums =IntStream.rangeClosed(1,10).boxed().collect(Collectors.toList());

        req.setAttribute("tag", tag);
        req.setAttribute("nums", nums);
*/
        String pageStr = req.getParameter("page");
        log.info("pageStr" +pageStr);


        int page = StringUtil.getInt(pageStr,1); //페이지 정보를 가지고 DB에서 데이터를 가지고 올 것이다.
        //DB 의 한줄 한줄 = TodoVO인스턴스

        List<TodoVO> todoList = TodoDAO.INSTANCE.list();


        PageInfo pageInfo = new PageInfo(page,10,131);
        req.setAttribute("pageInfo", pageInfo); //보내자

        //분배해준다. 여기로 택배 보내줘
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }
}
