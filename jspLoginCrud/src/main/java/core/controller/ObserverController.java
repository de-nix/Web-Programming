package core.controller;

import core.database.DB;
import core.domain.Content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObserverController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            ArrayList<String> moms =  DB.database.getMotherLine(username);
            PrintWriter pw = resp.getWriter();
            pw.print(moms);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            ArrayList<String> dads =  DB.database.getFatherLine(username);
            PrintWriter pw = resp.getWriter();
            pw.print(dads);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
