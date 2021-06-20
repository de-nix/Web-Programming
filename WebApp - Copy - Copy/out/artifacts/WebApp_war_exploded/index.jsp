<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 5/3/2020
  Time: 3:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web App Tutorial Page</title>
</head>
<body>

<labe>Give your username</labe>
<input type="text" id="name" name="name">
<button onclick="start()">Start!</button>

</body>
</html>

<script type="text/javascript">

    function start()
    {
        var name = document.getElementById("name").value;
        console.log(name);

        if(name.length==0)
        {
            console.log("Please enter something!");
            window.location.href = "http://localhost:8080/WebApp_war_exploded/index.jsp";
            return;
        }

        window.location.href = "http://localhost:8080/WebApp_war_exploded/welcome.jsp?name="+name;

    }

</script>
