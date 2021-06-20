package appLayer;

import dbLayer.MyConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class User {

    static Connection con;
    static PreparedStatement ps;

    private String username;
    private String password;
    private int id;

    public void registerUser(String sUsername, String sUserPassword)
    {
        try
        {
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("insert into user(username, password) values(?,?)");
            ps.setString(1, sUsername);
            ps.setString(2,sUserPassword);
            ps.execute();
            this.username = sUsername;
            this.password = sUserPassword;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public List<Asset> getAssetsOfSpecificUser(int user_id)
    {
        List<Asset> assets = new ArrayList<>();

        try{
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select * from asset where user_id=?");
            ps.setString(1, String.valueOf(user_id));

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int a_id = Integer.parseInt(rs.getString(1));
                int a_user_id = Integer.parseInt(rs.getString(2));
                String name = rs.getString(3);
                String description = rs.getString(4);
                int value = Integer.parseInt(rs.getString(5));

                Asset a = new Asset(a_id, a_user_id, name, description, value);

                assets.add(a);

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return assets;
    }

    public boolean isUser(String username)
    {
        try {
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select * from user where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                if(rs.getString(2).equals(username))
                {
                    return true;
                }
            }
            return false;

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    public boolean isValidCredentials(String sUserName, String sUserPassword)
    {
        try
        {
            System.out.println(sUserName);
            System.out.println(sUserPassword);

            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("select * from user where username=?");
            ps.setString(1,sUserName);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                this.id = Integer.parseInt(rs.getString(1));
                this.username = rs.getString(2);
                this.password = rs.getString(3);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        if(sUserPassword.equals(this.password))
        {
            return true;
        }
        return false;
    }

    public int getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPass()
    {
        return this.password;
    }

    public void setUsername(String un)
    {
        username = un;
    }

    public void setPass(String pass)
    {
        password = pass;
    }

}
