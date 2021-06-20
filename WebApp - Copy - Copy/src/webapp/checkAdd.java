package webapp;

import appLayer.PostController;
import appLayer.Topic;
import appLayer.TopicController;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "welcome.jsp")
public class checkAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PostController postController = new PostController();
        int nr = postController.getNrPosts();

        String json = new Gson().toJson(nr);
        response.getWriter().write(json);

    }

}
