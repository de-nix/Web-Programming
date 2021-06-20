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

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String mom= req.getParameter("mother");
        String dad = req.getParameter("father");
        RequestDispatcher rd;
        User user = null;
        try {
            user = DB.database.login(username,mom, dad);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("mom", mom);
            session.setAttribute("dad", dad);
            rd = req.getRequestDispatcher("/success.jsp");
            rd.forward(req,resp);
        }
        else{
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('The username or password is incorrect')</script>");
            rd = req.getRequestDispatcher("/login.html");
            rd.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
