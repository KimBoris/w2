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

       // List<Integer> nums = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        //IntStream.rageClosed(1,10).boxed() > 객체로 만든다.
        //이것을 리스트로 만든것 collect(Collectors.toList());
        //ArrayList선언하고 for문돌려야하고 add해줘야해서
        //StreamAPI라고 한다.
      //  req.setAttribute("nums", nums);

       // req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);

        String pageStr = req.getParameter("page");

        log.info("pageStr: " + pageStr);

        int page = StringUtil.getInt(pageStr, 1);

        try{

            int total = TodoDAO.INSTANCE.getTotal();

            PageInfo pageInfo = new PageInfo(page,10,total);

            List<TodoVO> list = TodoDAO.INSTANCE.list(pageInfo.getPage());

            req.setAttribute("list", list);

            log.info(list);

            req.setAttribute("pageInfo", pageInfo);

            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        }catch(Exception e){
            e.printStackTrace();
        }//end catch
    }



   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Exception을 던질 수 없다. Override 규칙에 위반된다.
        log.info("Todo List Get");

        *//* // String tag ="<script src='49.174.76.109/sample.js'></script>";



     *//**//*
        Scores scores = Scores.builder()
                .subject1(10)
                .subject2(20).subject3(30).subject4(40).subject5(50).build();
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
*//**//*
        //객체형으로 담아서 만든다.
        List<Integer> nums =IntStream.rangeClosed(1,10).boxed().collect(Collectors.toList());

        req.setAttribute("tag", tag);
        req.setAttribute("nums", nums);
*//*
     *//* String pageStr = req.getParameter("page");
        log.info("pageStr" + pageStr);


        int page = StringUtil.getInt(pageStr, 1); //페이지 정보를 가지고 DB에서 데이터를 가지고 올 것이다.
        //DB 의 한줄 한줄 = TodoVO인스턴스


        try {
            List<TodoVO> todoList = TodoDAO.INSTANCE.list(page);

            int total = TodoDAO.INSTANCE.getTotal();

            PageInfo pageInfo = new PageInfo(page, 10, total);

            req.setAttribute("list", TodoDAO.INSTANCE.list(pageInfo.getPage()));

            req.setAttribute("pageInfo", pageInfo); //보내자

            //분배해준다. 여기로 택배 보내줘
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
*//*

        Scores scores = Scores.builder().
                        subject1(10).
                        subject2(20).
                        subject3(30).
                        subject4(40).
                        subject5(50).
                        build();

        req.setAttribute("obj", scores);

        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }*/
}
