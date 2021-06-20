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

public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        RequestDispatcher rd =null;
        try {
            user = DB.database.authentication(user);
            if( user != null ){
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
                resp.getWriter().println("<script>alert('Login failed. Mismatch of email password.')</script>");
                rd = req.getRequestDispatcher("/login.html");
                rd.include(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
