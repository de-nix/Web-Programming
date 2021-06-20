package core.database;

import core.domain.Content;
import core.domain.User;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    public final static DB database = new DB();
    private static Statement statement;
    private final static String stringConnection = "jdbc:mysql://localhost/test2";
    private final static String user = "root";
    private final static String password = "";

    private DB() {
        connect();
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(stringConnection, user, password);
            DB.statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public User login(String username, String mom, String dad) throws SQLException {
        User user = null;
        ResultSet result = DB.statement.executeQuery("select * from users where username = '"+username+"'");
        if(result.next()){
            user = new User(result.getString("username"),result.getInt("id"));
        }
        if (user != null){
            ResultSet newResult = DB.statement.executeQuery("select * from familyrelations where username='"+username+"' and ( mother = '"+mom+"' or father = '"+dad+"')");
            if(newResult.next()) return user;
        }
        return null;

    }

    public ArrayList<String> getFatherLine(String username) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        while (!username.equals("")){

            ResultSet rs = statement.executeQuery("select * from familyrelations where username='"+username+"'");
            if (rs.next()){username = rs.getString("father");result.add(username);}
            else username = "";
        }
        return result;
    }

    public ArrayList<String> getMotherLine(String username) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        while (!username.equals("")){

            ResultSet rs = statement.executeQuery("select * from familyrelations where username='"+username+"'");
            if (rs.next()){username = rs.getString("mother");result.add(username);}
            else username = "";
        }
        return result;
    }


    public void insertFamily(String user, String mom, String dad) throws SQLException {
        DB.statement.executeUpdate("update familyrelations set mother = '"+mom+"', father = '"+dad+"' where username='"+user+"'");

    }
}
