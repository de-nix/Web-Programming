<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 5/3/2020
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="snake.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="button.css">
    <link rel="stylesheet" type="text/css" href="table.css">
</head>

<body>

    <%
        if(session.getAttribute("isLogin")==null)
        {
            response.sendRedirect("login.jsp");
        }
    %>

    <h1>Welcome: ${username}</h1>

    <form action="logout" method="post">
        <input type="submit" name="logout" value="Logout">
    </form>

    <table class="content-table" id="all_table">
        <thead>
        <tr>
            <th>id</th>
            <th>userid</th>
            <th>name</th>
            <th>description</th>
            <th>value</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <label>name:</label>
    <input type="text" id="name" name="name"><br><br>

    <label>description:</label>
    <input type="text" id="description" name="description"><br><br>

    <label>value:</label>
    <input type="text" id="value" name="value"><br><br>

    <button class="btn" onclick="addAssetLocally()">Add more!</button>

    <button onclick="addAsset()">Add more for real!</button>


</body>
</html>


<script>

        var array=[];

        function addAssetLocally()
        {
            console.log("Add asset locally!");
            var name = document.getElementById("name").value;
            var description = document.getElementById("description").value;
            var value = document.getElementById("value").value;

            var item = {id: 0, user_id: ${id}, name: name, description: description, value: value};

            array.push(item);

            console.log(array);

        }

        function addAsset() {

            var data_to_send = JSON.stringify(array);
            console.log(data_to_send);

            $.ajax({
                url: 'addAsset',
                type:'post',
                data: {'list': JSON.stringify(array)},
                success: function (data) {
                    get_assets();
                }
            });
        }

        function get_assets()
        {
            $("#all_table tbody").empty();

            $.ajax({
                url: 'getAssets?id='+${id},
                type: 'get',
                data: {},
                success: function (data) {
                    console.log(data);

                    for(var i=0; i<data.length; i++)
                    {
                        var id = data[i]['id'];
                        var user_id = data[i]['user_id'];
                        var name = data[i]['name'];
                        var descritption = data[i]['description'];
                        var value = data[i]['value'];

                        if(value > 10)
                        {
                            var asset = '<tr style="background-color:red">'+
                                '<td>'+id+'</td>'+
                                '<td>'+user_id+'</td>'+
                                '<td>'+name+'</td>'+
                                '<td>'+descritption+'</td>'+
                                '<td>'+value+'</td>'+
                                '</tr>';
                            $("#all_table tbody").append(asset);
                        }
                        else
                        {
                            var asset = '<tr>'+
                                '<td>'+id+'</td>'+
                                '<td>'+user_id+'</td>'+
                                '<td>'+name+'</td>'+
                                '<td>'+descritption+'</td>'+
                                '<td>'+value+'</td>'+
                                '</tr>';
                            $("#all_table tbody").append(asset);
                        }
                    }
                }
            });
        }

        $(document).ready(function()
        {
            get_assets();
        });

</script>
