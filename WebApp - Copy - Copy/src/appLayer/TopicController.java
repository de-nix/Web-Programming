package appLayer;

import dbLayer.MyConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TopicController {

    static Connection con;
    static PreparedStatement ps;

    public List<Topic> getTopics()
    {
        List<Topic> topics = new ArrayList<>();

        try{
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select * from topic;");

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int t_id = Integer.parseInt(rs.getString(1));
                String topicname = rs.getString(2);

                Topic t = new Topic(t_id, topicname);

                topics.add(t);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return topics;
    }

    public void addTopic(String topicname)
    {
        try{
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("insert into topic(topicname) values (?);");

            ps.setString(1,topicname);

            ps.execute();

            System.out.println("Non-existent topic inserted");

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Topic getTopicByName(String topicname)
    {
        try{
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select * from topic where topicname=?;");

            ps.setString(1, topicname);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                int t_id = Integer.parseInt(rs.getString(1));
                String topicname2 = rs.getString(2);

                return new Topic(t_id, topicname2);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return null;
    }

}
