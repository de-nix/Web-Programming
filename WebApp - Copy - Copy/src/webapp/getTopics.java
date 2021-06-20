package webapp;

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
public class getTopics extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TopicController topicController = new TopicController();
        List<Topic> topics = topicController.getTopics();

        topics.forEach(topic -> {
            System.out.println(topic
            );
        });

        String json = new Gson().toJson(topics);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

}
