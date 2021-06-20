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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "welcome.jsp")
public class updatePost extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String text = request.getParameter("text");
        String user = request.getParameter("user");
        String time = request.getParameter("time");
        String post_id = request.getParameter("postId");

        System.out.println(text);
        System.out.println(user);
        System.out.println(time);
        System.out.println(post_id);

        PostController postController = new PostController();

        System.out.println("Before post");

        postController.updatePost(text, user, time, post_id);

        System.out.println("Post updated successfully");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
