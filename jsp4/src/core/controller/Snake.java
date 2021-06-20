package core.controller;

import core.domain.DB;

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

public class Snake extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x = Integer.parseInt(req.getParameter("x"));
        int y = Integer.parseInt(req.getParameter("y"));
        RequestDispatcher rd;
        ArrayList<Point> snake = DB.database.getSnake();
        ArrayList<Point> rocks = DB.database.getRocks();
        ArrayList<Point> cherries = DB.database.getCherry();
        try {

            Point newPos = new Point(snake.get(0).x + x, snake.get(0).y + y);

            if(newPos.x>9){newPos.x=0;}if(newPos.x<0){newPos.x=9;}if(newPos.y>9){newPos.y=0;}if(newPos.y<0){newPos.y=9;}
            Point lastPoint = snake.get(snake.size() - 1);
            if (rocks.contains(newPos)) {
                rd = req.getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
            } else if (cherries.contains(newPos)) {
                System.out.println("cireasa");
                DB.database.moveSnake(newPos,snake.size());
                DB.database.addSnake(lastPoint, snake.size()+1);
                DB.database.deleteCherry(newPos);
            } else {


                DB.database.moveSnake(newPos, snake.size());
            }


        snake = DB.database.getSnake();
        HttpSession session = req.getSession();
        session.setAttribute("snake", snake);
            session.setAttribute("rocks", rocks);
            session.setAttribute("cherries", cherries);
            rd  =req.getRequestDispatcher("/index.jsp");
            rd.forward(req,resp);
    }catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
