package core.domain;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DB {

    private Statement stmt;
    static final String connection = "jdbc:mysql://localhost/snake";
    static final String user = "root";
    static final String password = "";
    static final public DB database = new DB();

    private DB() {
        connect();
    }

    public void connect() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB.connection, DB.user, DB.password);
            this.stmt = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) throws SQLException {
        stmt.executeUpdate("insert into users(email, password) values('" + user.getEmail() + "','" + user.getPassword() + "')");

    }

    public void addSnake(Point point, int position) throws SQLException {
        stmt.executeUpdate("insert into snakePoints(x,y,pos) values(" + point.x + "," + point.y + "," + position + ")");
    }

    public void moveSnake(Point point, int i) throws SQLException {

        stmt.executeUpdate("delete from snakePoints where pos = " + i);
        stmt.executeUpdate("update snakePoints set pos = pos+1");
        stmt.executeUpdate("insert into snakePoints(x,y,pos) values(" + point.x + "," + point.y + ", 1)");
    }


    public void deleteCherry(Point point) throws SQLException {
        stmt.executeUpdate("delete from cherryPoints where x = " + point.x + " and y= " + point.y);
    }


    public User authentication(User user) throws SQLException {
        ResultSet resultSet = stmt.executeQuery("select * from users where email ='" + user.getEmail() + "'");
        if (resultSet.next()) {
            if (resultSet.getString("password").equals(user.getPassword())) {
                User newUser = new User();
                newUser.setId(resultSet.getInt("id"));
                newUser.setEmail(resultSet.getString("email"));
                newUser.setPassword(resultSet.getString("password"));
                return newUser;
            }
        }
        return null;
    }

    public boolean uniqueEmail(User user) throws SQLException {
        System.out.println(user);
        ResultSet resultSet = stmt.executeQuery("select * from users where email ='" + user.getEmail() + "'");
        return !resultSet.next();

    }

    public void deleteUser(User user) throws SQLException {

        stmt.executeUpdate("delete from users where id=" + user.getId());

    }

    public ArrayList<Point> getSnake() {
        ArrayList<Point> snake = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select * from snakePoints order by pos");
            while (rs.next()) {
                snake.add(new Point(rs.getInt("x"), rs.getInt("y")));
            }
            rs.close();
            return snake;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return snake;
    }

    public ArrayList<Point> getCherry() {
        ArrayList<Point> cherry = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select * from cherryPoints");
            while (rs.next()) {
                cherry.add(new Point(rs.getInt("x"), rs.getInt("y")));
            }
            rs.close();
            return cherry;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cherry;
    }

    public ArrayList<Point> getRocks() {
        ArrayList<Point> rocks = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("select * from rockPoints");
            while (rs.next()) {
                rocks.add(new Point(rs.getInt("x"), rs.getInt("y")));
            }
            rs.close();
            return rocks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rocks;
    }

}
