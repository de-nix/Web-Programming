package appLayer;

import com.google.gson.annotations.SerializedName;
import dbLayer.MyConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Asset {

    static Connection con;
    static PreparedStatement ps;

    @SerializedName("id")
    private int id;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("value")
    private int value;

    public Asset()
    {

    }

    public void addAsset(int user_id, String name, String description, int value)
    {
        try
        {
            con = MyConnectionProvider.getCon();
            ps = con.prepareStatement("insert into asset(user_id, name, description, value) values (?,?,?,?);");
            ps.setString(1, String.valueOf(user_id));
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setString(4, String.valueOf(value));

            ps.execute();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Asset(int id, int user_id, String name, String description, int value)
    {
        this.id=id;
        this.user_id = user_id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public int getId()
    {
        return id;
    }

    public int getUser_id()
    {
        return this.user_id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public int getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return "Asset: {id="+id+", user_id="+user_id+", name="+name+", description="+description+", value="+value+"}";
    }
}
