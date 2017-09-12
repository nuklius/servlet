package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.entity.user;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        String final_print;

        MainFacade qwe = new MainFacade();

        String param_task = req.getParameter("task");

        switch (param_task) {
            case "reg":
                final_print = qwe.registration(req.getParameter("nick"), req.getParameter("pass"));
                break;

            case "log_in":
                final_print = qwe.logginIn(req.getParameter("userId"), req.getParameter("lang"));
                break;

            case "new_quest":
                final_print = qwe.takingNewQuest(req.getParameter("userId"));
                break;

            case "done_it":
                final_print = qwe.doneQuest(req.getParameter("userId"));
                break;

            case "propose_text":
                final_print = qwe.proposeText(req.getParameter("userId"), req.getParameter("text"));
                break;

            case "get_top":
                final_print = qwe.getTopUsers();
                break;

            case "rate_task":
                final_print = qwe.rateQuest(req.getParameter("userId"), Integer.parseInt(req.getParameter("rate")));
                break;

            default:
                final_print = "error";
                break;
        }

        pw.println(final_print);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        try (SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory(); Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            
            /*
            добавление нового пользвотеля
            //user newUser = new user();

            //newUser.setLogin("qweww");
            //newUser.setPassword("qweww");

            //session.save(newUser);
            
            transaction.commit();

            */
            
            /* можно проверить статус успешен/нет транзакции
            if (transaction.getStatus().toString() == "COMMITTED") {
                pw.println(student.getLogin());
            } else if (transaction.getStatus().toString() == "FAILED_COMMIT") {
                pw.println(student.toString() + transaction.getStatus().toString());
            } else {
                pw.println(student.toString() + transaction.getStatus().toString());
            }*/
            
            /*вытащить данные из базы данных
            так же потоп через student.set можно изменять значения ячеек
            
            user student = session.load(user.class, 3);       
            transaction.commit();
            
            */
            
            session.close();
            sessionFactory.close();
        }
        //pw.println("error");
        //pw.println("error");

    }

}
