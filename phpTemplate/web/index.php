<?php
require_once '../model/User.php';

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
<?php echo "<h1>welcome " . $_SESSION["user"]. "</h1>";?>

    <table id="Bigtable"></table>

    <p id="file"></p>

    <section>
        Page: <input type="number" id="value">
        <button onclick="func()">get Page</button>
    </section>

<script>
    let assets = []
    let allrows = []
    function mode(brr){
        let arr = brr;
        return arr.sort((a,b) =>
            arr.filter(v => v===a).length
            - arr.filter(v => v===b).length
        ).pop();
    }

    function getBigRowString(ass) {

        console.log("bigRow");
        let rows = "";
        ass.forEach(x => rows = rows + "<tr><td>" + x.filename + "</td><td>" + x.filepath + "</td><td>" + x.size + "</td></tr>");
        return rows;
    }

    function showBigTable(ass) {

        document.getElementById("Bigtable").outerHTML =
            "<table id=\"Bigtable\"><tr><td>Name</td><td>Path</td><td>Size</td></tr>" + getBigRowString(ass) + "</table>";
    }

    function func() {
        let p = document.getElementById("value").value;
        console.log(p);
        let ty = allrows.slice(p*5,p*5+5);
        ty.forEach(x => assets.push(x.filename));
        reset();
        showBigTable(ty);
    }

    function reset(){
        let filename = mode(assets);
        document.getElementById("file").outerHTML = "<p id=\"file\">"+filename+"</p>";

    }

    function showTable() {

        console.log("shBigTable");
        $.ajax({
            type: "GET",
            url: "../controller/Controller.php",
            data: {
                action : "getAssets",
                id: <?php echo $user->getId()?>
            },
            data_type: 'json',
            success: function (data) {
                let parsedData = JSON.parse(data);
                allrows = parsedData;
                let r = parsedData.slice(0,5);
                r.forEach(x => assets.push(x.filename));
                reset();
                console.log(assets);
                showBigTable(r);
            }
        });
    }

    $(document).ready(function () {
        showTable();

    });


</script>
</body>
</html>
