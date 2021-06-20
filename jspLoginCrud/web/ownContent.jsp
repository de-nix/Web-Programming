<%@ page import="core.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: d3nis
  Date: 6/20/2020
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) response.sendRedirect("/login.html");%>

<table id="creationTable">

</table>

<style>


</style>
<script>
    var items = []

    function deleteItem(id){
        console.log(id);
        $.ajax(
            {
                url: "MainController",
                method : "get",
                data : {
                    myid : ""+id
                }
            });
        showData();
    }


    function showData(){
        $.ajax(
            {
                url: "MainController",
                method : "get",
                data : {
                    id : "<%=user.getId()%>"
                },
                success: function(data){
                    let newHtml = "<table id = \"creationTable\"><tr><td>Title</td><td>Description</td><td>Url</td>" +
                        "<td>Data</td><td>Remove</td></tr>";
                    const parsed = JSON.parse(data);
                    console.log(parsed);
                    parsed.forEach(
                        x => {
                            newHtml += "<tr><td>"+x.title+"</td><td>"+x.description+"</td><td>"+x.url+"</td><td>"+x.date
                                +"</td><td><button onclick = \"deleteItem("+x.id+")\">Remove Content</button></td></tr>"
                        }
                    )
                    newHtml += "</table>";
                    document.getElementById("creationTable").outerHTML = newHtml;
                }
            }
        );
    }


    $(document).ready(
      showData()
    )

</script>

</body>
</html>
