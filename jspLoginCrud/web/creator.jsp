<%@ page import="core.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: d3nis
  Date: 6/19/2020
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creator</title>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) response.sendRedirect("/login.html");%>

<button onclick="location.href = 'ownContent.jsp'"> See Own Content</button>

<section>
    <label>Title: <input type="text" required id="title"></label>
    <label>Description: <input type="text" required id="description"></label>
    <label>Url: <input type="text" required id="url"></label>
    <input id="addB" type="submit" value="Add new Content">
</section>

<table id="table"></table>
<button id="submitData">Submit Data</button>
<script>
    document.getElementById("submitData").setAttribute("disabled", "true");

    function resetTable() {
        console.log("fct");
        document.getElementById("table").outerHTML = "<table id=\"table\"><tr><td>Title</td><td>Description</td><td>Url</td></tr>" +
            getRowString() +
            "</table>"
    }

    function getRowString() {
        let rows = "";
        contents.forEach(x => rows = rows + "<tr><td>" + x.title + "</td><td>" + x.description + "</td><td>" + x.url + "</td></tr>")
        return rows;
    }

    let contents = [];
    $("#addB").click(function () {
        let content = {
            "title": document.getElementById("title").value,
            "description": document.getElementById("description").value,
            "url": document.getElementById("url").value,
            "idUser":<%=user.getId()%>
        };
        console.log(content);
        contents.push(content);
        resetTable();
        document.getElementById("submitData").removeAttribute("disabled");
    });

    $("#submitData").click(function () {

        document.getElementById("submitData").setAttribute("disabled", "true");

        $.ajax(
            {
                url: "MainController",
                method: "POST",
                data_type: "json",
                data: {
                    arr:JSON.stringify(contents)
                },
                success(data){
                    console.log("sent items");
                    console.log(data);
                }


            }
        )
        document.getElementById("table").outerHTML = "<table id=\"table\"></table>" ;

    })

</script>
</body>
</html>
