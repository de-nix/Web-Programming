<?php
session_start();
?>
<!doctype html>
<html>

<head>
    <title>Login</title>
</head>

<body>


<form action="../controller/Controller.php" method="post">
    <label> Username: <input name="username" required></label>
    <label> Password: <input type="password" name="password" required></label>
    <input type="hidden" value="login" name="action">
    <input type="submit" value="Login">
</form>




</body>

</html>
