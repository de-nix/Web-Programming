package core.controller;

import core.domain.DB;
import core.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        System.out.println(user);
        user.setEmail(email);
        System.out.println(user);
        user.setPassword(password);
        System.out.println(user);
        user.setId(-1);
        RequestDispatcher rd =null;
        try {
            if(DB.database.uniqueEmail(user)){
                DB.database.addUser(user);
                user = DB.database.authentication(user);
                HttpSession session = req.getSession();
                ArrayList<Point> snake = DB.database.getSnake();
                ArrayList<Point> rocks = DB.database.getRocks();
                ArrayList<Point> cherries = DB.database.getCherry();
                session.setAttribute("user", user);
                session.setAttribute("snake", snake);
                session.setAttribute("rocks", rocks);
                session.setAttribute("cherries", cherries);
                rd  =req.getRequestDispatcher("/index.jsp");
                rd.forward(req,resp);

            }
            else{
                resp.getWriter().println("<script>alert('Register failed. Email already exist.')</script>");
                rd = req.getRequestDispatcher("/register.html");
                rd.include(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
