package appLayer;

import dbLayer.MyConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PostController {

    static Connection con;
    static PreparedStatement ps;

    public int getNrPosts()
    {
        try
        {
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select count(*) from Post");

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                int c =Integer.parseInt(rs.getString(1));
                return c;
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    public void addPost(String user, int topicid, String text, Long date)
    {
        try
        {
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("insert into post(user, topicid, text, date) values (?,?,?,?);");
            ps.setString(1,user);
            ps.setString(2,String.valueOf(topicid));
            ps.setString(3,text);
            ps.setString(4,String.valueOf(date));

            ps.execute();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void updatePost(String text, String user, String time, String post_id)
    {
        try
        {
            con = MyConnectionProvider.getCon();

            ps = con.prepareStatement("update post set user=?, text=?, date=? where id=?;");
            ps.setString(1, user);
            ps.setString(2,text);
            ps.setString(3,time);
            ps.setString(4,post_id);

            ps.execute();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
