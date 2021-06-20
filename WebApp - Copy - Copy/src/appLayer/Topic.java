package appLayer;

import com.google.gson.annotations.SerializedName;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Topic {
    static Connection con;
    static PreparedStatement ps;

    @SerializedName("id")
    private int id;

    @SerializedName("topicname")
    private String topicname;

    public int getId()
    {
        return this.id;
    }

    public String getTopicname()
    {
        return this.topicname;
    }

    public Topic()
    {

    }

    public Topic(int id, String topicname)
    {
        this.id = id;
        this.topicname = topicname;
    }

    public String toString()
    {
        return "Topic: {id="+id+", topicname="+topicname+"}";
    }


}
