<!DOCTYPE html>
<html>
<head>
    <title>Welcome!</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="button.css">
    <link rel="stylesheet" type="text/css" href="table.css">
</head>
<body>

<table class="content-table" id="all_table">
    <thead>
    <tr>
        <th>TopicName</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<table class="content-table" id="posts_table">
    <thead>
    <tr>
        <th>User</th>
        <th>TopicId</th>
        <th>Text</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<label>Topic name</label>
<input type="text" id = "topic_name" name="topic_name">
<label>Text</label>
<input type="text" id="text" name="text">
<button onclick="addPost()">Add post!</button>

<br>
<br>
<br>

<label>PostId</label>
<input type="text" id="postId" name="">

<label>Text</label>
<input type="text" id="text_update" name="">
<button onclick="update()">Update</button>

</body>
</html>

<script type="text/javascript">

    var added = 0;

    function update()
    {
        var text = document.getElementById("text_update").value;
        var post_id = document.getElementById("postId").value;

        var url_string = window.location.href;
        var url = new URL(url_string);
        var c = url.searchParams.get("name");
        console.log(c);

        var time = Math.floor(Date.now() / 1000);

        $.ajax({
            url: 'update_post',
            type: 'post',
            data: {'text': text, 'user': c, 'time': time, 'postId': post_id},
            success: function (data) {

                console.log(data);
                alert("successfully modified!");

            }
        });

    }

    function addPost()
    {
        var topic_name = document.getElementById("topic_name").value;
        var text = document.getElementById("text").value;

        var url_string = window.location.href;
        var url = new URL(url_string);
        var c = url.searchParams.get("name");

        var time = Math.floor(Date.now() / 1000);

        $.ajax({
            url: 'addPost',
            type: 'post',
            data: {'topic_name': topic_name, 'text': text, 'user': c, 'time': time},
            success: function (data) {
                added=1;
            }
        });

    }

    function show_posts(id)
    {
        console.log(id);
        console.log("Show posts");

        $("#posts_table tbody").empty();

        $.ajax({
            url: 'getPosts.php',
            type: 'get',
            data: {'id': id},
            success: function (data) {

                console.log(data);
                console.log(data.length);
                var myJsonString = JSON.stringify(data);
                console.log(myJsonString);
                var data2 = JSON.parse(myJsonString);

                for(var i=0; i<data2.length; i++)
                {
                    var id = data2[i]['id'];
                    var user = data2[i]['user'];
                    var topicId = data2[i]['topicid'];
                    var text = data2[i]['text'];
                    var date = data2[i]['date'];

                    var name = data2[i]['topicname'];
                    var topic = '<tr>'+
                        '<td>'+user+'</td>'+
                        '<td>'+topicId+'</td>'+
                        '<td>'+text+'</td>'+
                        '<td>'+date+'</td>'+
                        '</tr>';
                    $("#posts_table tbody").append(topic);
                }
            }
        });

    }


    function getTopics()
    {
        console.log("get_topics");

        $("#all_table tbody").empty();

        var url_string = window.location.href;
        var url = new URL(url_string);
        var c = url.searchParams.get("name");

        $.ajax({
            url: 'getTopics',
            type: 'get',
            data: {},
            success: function (data) {

                console.log(data);
                console.log(data.length);

                for(var i=0; i<data.length; i++)
                {
                    var id = data[i]['id'];
                    var name = data[i]['topicname'];
                    var topic = '<tr>'+
                        '<td>'+name+'</td>'+
                        '<td><button onclick=show_posts('+id+')>Show posts</button>'+
                        '</tr>';
                    $("#all_table tbody").append(topic);
                }
            }
        });

    }

    $(document).ready(function()
    {
        var current_rows = 0;

        /*$.ajax({
            url: 'checkAdd.php',
            type: 'get',
            data: {},
            success: function (data) {

                current_rows=data;

            }
        });*/

        getTopics();

        window.setInterval(function(){
            $.ajax({
                url: 'checkAdd',
                type: 'get',
                data: {},
                success: function (data) {

                    console.log(data);
                    if(data!=current_rows && added==0)
                    {
                        alert("Just added!");
                        current_rows = data;
                    }
                    else
                        console.log("Nothing yet");
                }
            });
        }, 1000);

    });


</script>
