<?php

session_start();
?>
<!doctype html>
<html>

<head>
    <title>Register</title>
</head>

<body>

<button onclick="window.location.href = 'login.php';">Login</button>

<form action="../controller/Controller.php" method="post">
    <label> Username: <input name="username" required></label>
    <label> Password: <input type="password" name="password" required></label>
    <input type="hidden" value="register" name="action">
    <input type="submit" value="Register">
</form>




</body>

</html>
