package appLayer;

import com.google.gson.annotations.SerializedName;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Post {

    static Connection con;
    static PreparedStatement ps;

    @SerializedName("user")
    private String user;

    @SerializedName("topicid")
    private int topicid;

    @SerializedName("text")
    private String text;

    @SerializedName("date")
    private Long date;

    public Post(String user, int topicid, String text, Long date)
    {
        this.user=user;
        this.topicid=topicid;
        this.text=text;
        this.date=date;
    }

    public Post()
    {

    }

}
