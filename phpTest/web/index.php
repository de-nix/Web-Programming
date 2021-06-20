<?php
require_once '../model/Topic.php';

session_start();
if($_SESSION["user"]== null ){
    echo "<script>window.location.href = 'login.php'</script>";
}
$user = $_SESSION["user"]


?>
<html>
<head>
    <title>Index</title>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>

</head>
<body>

<section>
    id <input id="id">
    text <input id="text">
    topicid <input id="topic">
    <button id="updatePost">update</button>

</section>
<section>
    text <input id="textADD">
    topic name <input id="topicADD">
    <button id="addPost">add</button>

</section>



<script>

    var no =-1;
    $("#updatePost").click(function () {

        $.ajax(
            {
                url: "../controller/Controller.php",
                method: "POST",
                data_type: "json",
                data: {
                    action: "updatePost",
                    id: document.getElementById("id").value,
                    date: <?php echo '"'.time().'"'?>,
                    user: <?php echo '"'.$user.'"'?>,
                    text: document.getElementById("text").value,
                    topic: document.getElementById("topic").value,
                }
            }
        )

    });
    $("#addPost").click(function () {

        $.ajax(
            {
                url: "../controller/Controller.php",
                method: "POST",
                data_type: "json",
                data: {
                    action: "addPost",
                    date: <?php echo '"'.time().'"'?>,
                    user: <?php echo '"'.$user.'"'?>,
                    text: document.getElementById("textADD").value,
                    topic: document.getElementById("topicADD").value,
                }
            }
        )

    });


    function showAdd(){
        $.ajax(
            {
                url: "../controller/Controller.php",
                method: "POST",
                data_type: "json",
                data: {
                    action: "refresh"
                },
                success : function(data){
                    if(data!==no){
                        no = data;
                        alert("new data was added");
                    }
                }
            }
        )

    }

    $(document).ready(function () {
        setTimeout(showAdd, 500);


    });


</script>
</body>
</html>
