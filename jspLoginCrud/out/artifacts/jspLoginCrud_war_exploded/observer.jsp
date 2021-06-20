<%@ page import="core.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: d3nis
  Date: 6/18/2020
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
  </head>
  <body>
  <% User user = (User) session.getAttribute("user");
    if(user == null)response.sendRedirect("/login.html");%>

  <table id="creationTable">

  </table>


  <script>
    let items = []
    let mostRecentId = -1

    function showData(){
      let newHtml = "<table id = \"creationTable\"><tr><td>Title</td><td>Description</td><td>Url</td>" +
              "<td>Data</td></tr>";

      items.forEach(
              x => {
                newHtml += "<tr><td>"+x.title+"</td><td>"+x.description+"</td><td>"+x.url+"</td><td>"+x.date
                        +"</td></tr>"
              }
      )
      newHtml += "</table>";

      document.getElementById("creationTable").outerHTML = newHtml;
      $("table tr").hide();
      $("table tr").each(function(index){
        $(this).delay(index*500).show(1000);
      })

    }



    function request(){
      $.ajax(
              {
                url: "ObserverController",
                method : "get",
                data : {
                  id : mostRecentId
                },
                success: function(data){
                  if(data!= null){
                  const parsed = JSON.parse(data);
                  items = [];
                  parsed.forEach(u=>items.push(u));
                  if(items[0] !=null){
                    mostRecentId = items[0].id;
                  }
                  showData()
                  }
                }
              }
      );
    }


    $(document).ready(
            setInterval(request, 1000)
    )

  </script>
  </body>
</html>
