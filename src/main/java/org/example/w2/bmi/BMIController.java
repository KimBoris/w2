package org.example.w2.bmi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value={"/bmi"})
public class BMIController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/bmiInput.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/WEB-INF/bmiResult.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
