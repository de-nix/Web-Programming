<%@ page import="java.awt.geom.Point2D" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.awt.*" %><%--
  Created by IntelliJ IDEA.
  User: d3nis
  Date: 6/16/2020
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>$Title$</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<%
    ArrayList<Point> snakePoints = (ArrayList<Point>) session.getAttribute("snake");
    List<Point> cherryPoints = (List<Point>) session.getAttribute("cherries");
    List<Point> rocksPoints = (List<Point>) session.getAttribute("rocks");
    Point direction = new Point(0, -1);
%>
<canvas id="canvas" width="200" height="200"></canvas>
$END$
<script type="text/javascript">
    var canvas = document.getElementById('canvas');
    var ctx = canvas.getContext('2d');

    function drawEmptySquare(x, y) {
        realX = x * 20;
        realY = y * 20;
        ctx.beginPath();
        ctx.strokeRect(realX, realY, 20, 20);
    }

    function drawSnakeSquare(x, y) {
        realX = x * 20;
        realY = y * 20;
        ctx.beginPath();
        ctx.fillStyle = 'green';
        ctx.fillRect(realX, realY, 20, 20);
    }

    function drawCherrySquare(x, y) {
        realX = x * 20;
        realY = y * 20;
        ctx.beginPath();
        ctx.fillStyle = 'red';
        ctx.fillRect(realX, realY, 20, 20);
    }

    function drawRockSquare(x, y) {
        realX = x * 20;
        realY = y * 20;
        ctx.beginPath();
        ctx.fillStyle = 'blue';
        ctx.fillRect(realX, realY, 20, 20);
    }

    function drawBoard() {
        for (let i = 0; i < 10; i++) {
            for (let j = 0; j < 10; j++) {
                drawEmptySquare(i, j);
            }
        }
    }

    function drawSnake() {
        <%for(Point point : snakePoints){%>
        drawSnakeSquare(<%=point.getX()%>, <%=point.getY()%>);
        <%}%>
    }

    function drawCherry() {
        <%for(Point point : cherryPoints){%>
        drawCherrySquare(<%=point.getX()%>, <%=point.getY()%>);
        <%}%>
    }

    function drawRocks() {
        <%for(Point point : rocksPoints){%>
        drawRockSquare(<%=point.getX()%>, <%=point.getY()%>);
        <%}%>
    }

    $(document).ready(function f() {
        function loop() {
            setTimeout(function () {
                console.log("he");
                ctx.clearRect(0, 0, 200, 200);
                drawBoard();
                drawSnake();
                drawCherry();
                drawRocks();

                $.ajax(
                    {
                        url: "/Snake",
                        data: {
                            x: <%=direction.x%>,
                            y: <%=direction.y%>
                        },
                        type: "get"
                    }
                );
                requestAnimationFrame(loop);
            }, 1000);
        }
        

        loop();
    });


</script>
</body>
</html>
