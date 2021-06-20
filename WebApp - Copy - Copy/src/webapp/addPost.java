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
public class addPost extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String topic_name = request.getParameter("topic_name");
        String text = request.getParameter("text");
        String user = request.getParameter("user");
        String time = request.getParameter("time");

        PostController postController = new PostController();
        TopicController topicController = new TopicController();

        List<Topic> topics = topicController.getTopics();

        Optional<Topic> t = topics.stream().filter(topic -> topic.getTopicname().equals(topic_name)).findFirst();

        if(t.isPresent())
        {
            Topic to = t.get();

            int topic_id = to.getId();

            postController.addPost(user, topic_id, text, Long.valueOf(time));

            System.out.println("Post just added for existing topic");

        }
        else
        {
            topicController.addTopic(topic_name);
            Topic tp = topicController.getTopicByName(topic_name);
            postController.addPost(user, tp.getId(), text, Long.valueOf(time));

            System.out.println("Post just added for non-existing topic");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
