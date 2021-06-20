package webapp;

import appLayer.Asset;
import appLayer.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getAssets")
public class getAssets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = Integer.valueOf(request.getParameter("id"));

        request.setAttribute("user_id", user_id);

        User user = new User();

        List<Asset> assets = user.getAssetsOfSpecificUser(user_id);

        String json = new Gson().toJson(assets);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
