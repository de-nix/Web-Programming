package webapp;

import appLayer.Asset;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

@WebServlet(name = "addAsset")
public class addAsset extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Asset asset = new Asset();

        String json = request.getParameter("list");
        Type listType = new TypeToken<List<Asset>>() {}.getType();
        List<Asset> yourList = new Gson().fromJson(json, listType);

        for(int i=0; i<yourList.size(); i++)
        {
            asset.addAsset(yourList.get(i).getUser_id(), yourList.get(i).getName(), yourList.get(i).getDescription(), yourList.get(i).getValue());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
