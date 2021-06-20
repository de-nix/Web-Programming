<%@ page import="core.database.DB" %>
<%@ page import="core.domain.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: d3nis
  Date: 7/10/2020
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    String mother = (String)session.getAttribute("mom");
    String father = (String)session.getAttribute("dad");


%>
<table id="dads" >
    <tr><th>Father Line</th></tr>
    <%
        ArrayList<String> dads = null;


            try {
                dads = DB.database.getFatherLine(user.getUsername());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        if (dads!=null){
            for (String dad: dads
                 ) {
                %>
    <tr>
    <td><%=dad%></td>
    </tr>
    <%
            }
        }
    %>
</table>

<table id="moms">
    <tr><th>Mother Line</th></tr>
    <%
        ArrayList<String> moms = null;


            try {
                moms = DB.database.getMotherLine(user.getUsername());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        if (moms!=null){
            for (String mom: moms
                 ) {
                %>
    <tr>
    <td><%=mom%></td>
    </tr>
    <%
            }
        }
    %>

</table>

<form action="MainController" method="post">
    <input type="hidden" value="<%=mother%>" name="mom">
    <input type="hidden" value="<%=father%>" name="dad">
    <input type="hidden" value="<%=user.getUsername()%>" name="username">
    <input type="submit" value="add parents in database">
</form>

<script>
    function updateTableDads(){
        $.ajax(
            {
                method:"Post",
                url : "ObserverController",
                data : {username: <%=user.getUsername()%>},
                success: function (data){
                 const names = JSON.parse(data);

                    if( document.getElementById("dads").rows.length !== names.size){
                 let htmlText = "<table id=\"dads\" >\n" +
                     "    <tr><th>Father Line</th></tr>";
                 names.forEach(x => htmlText = htmlText+ "<tr><td>"+x+"</td></tr>");
                 htmlText = htmlText+"</table>";
                 document.getElementById("dads").outerHTML = htmlText;
                        alert("the family tree was modified");
                    }
                }

            }
        )}
function updateTableMoms(){
        $.ajax(
            {
                method:"GET",
                url : "ObserverController",
                data : {username: <%=user.getUsername()%>},
                success: function (data){
                 const names = JSON.parse(data);
                  if( document.getElementById("moms").rows.length !== names.size){
                 let htmlText = "<table id=\"moms\" >\n" +
                     "    <tr><th>Mother Line</th></tr>";
                 names.forEach(x => htmlText = htmlText+ "<tr><td>"+x+"</td></tr>");
                 htmlText = htmlText+"</table>";
                 document.getElementById("moms").outerHTML = htmlText;
                 alert("the family tree was modified");
                  }
                }

            }
        )

    }
    $(document).ready(
        function () {
            setInterval(updateTableDads,500);
            setInterval(updateTableMoms,500);
        }
    )



</script>

</body>
</html>
