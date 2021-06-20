package core.controller;

import core.database.DB;
import core.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
        RequestDispatcher rd;
        try {
            if(DB.database.uniqueuser(username)){
                User user = DB.database.addUser(username, password, role);
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                if (user.getRole()==0) rd = req.getRequestDispatcher("/observer.jsp");
                else rd = req.getRequestDispatcher("/creator.jsp");
                rd.forward(req,resp);
            }
            else {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('The username already exists')</script>");
                rd = req.getRequestDispatcher("/register.html");
                rd.forward(req,resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
