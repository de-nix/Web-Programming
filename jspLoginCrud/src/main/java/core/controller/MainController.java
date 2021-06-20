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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mom = req.getParameter("mom");
        String dad = req.getParameter("dad");
        String user = req.getParameter("username");
        try {
            DB.database.insertFamily(user,mom,dad);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("success.jsp");
    }

    public ArrayList<Content> getArrayFromString(String x){
        ArrayList<Content> result = new ArrayList<>();
        x = x.replace('[','`');
        System.out.println(x);
        x = x.replace(']','`');
        System.out.println(x);
        x = x.replace('"','`');
        System.out.println(x);
        x = x.replace("`","");
        System.out.println(x);
        x = x.replace("},{","`");
        System.out.println(x);
        x = x.replace("}","");
        System.out.println(x);
        x = x.replace("{","");
        System.out.println(x);
        String[] objs = x.split("`");
        System.out.println(objs);
        for (String obj : objs) {
            String[] fields = obj.split(",");
            Content content = new Content();
            String[] pair = fields[0].split(":");
            content.setUsername(pair[1]);
            pair = fields[1].split(":");
            content.setMother(pair[1]);
            pair = fields[2].split(":");
            content.setFather(pair[1]);
            pair = fields[3].split(":");
            result.add(content);
        }
       return result;


    }


}
