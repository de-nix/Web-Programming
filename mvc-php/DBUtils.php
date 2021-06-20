<?php


class DBUtils
{
    private $host = 'localhost';
    private $db = 'students2';
    private $user = 'root';
    private $pass = '';
    private $charset = 'utf8';

    private $pdo;
    private $error;

    public function __construct()
    {
        $dsn = "mysql:host=$this->host;dbname=$this->db;charset=$this->charset";
        $opt = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->pass, $opt);
        } // Catch any errors
        catch (PDOException $e) {
            $this->error = $e->getMessage();
            echo "Error connecting to DB: " . $this->error;
        }
    }


    public function selectUser($name)
    {
        $stmt = $this->pdo->query("SELECT * FROM Students where name= '" . $name . "'");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectAllUsers()
    {
        $stmt = $this->pdo->query("SELECT * FROM Students;");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectUsersByRole($role)
    {
        $stmt = $this->pdo->query("SELECT * FROM Students where role= '" . $role . "'");
        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }


    public function insert($id, $name, $username, $password, $age, $role, $profile, $email, $webpage)
    {
        $this->pdo->exec("INSERT into students values(" . $id . ",'" . $name . "','" . $username . "','" . $password . "'," . $age . ",'" . $role . "',' " . $profile . "','" . $email . "','" . $webpage . "');");
//		$affected_rows = $this->pdo->exec("INSERT into student values(" . $id . ",'" . $name ."', ".$age.");");
//		return $affected_rows;
    }

    public function delete($id)
    {
        $this->pdo->exec("DELETE from students where ID=" . $id);

    }

    public function update($id, $name, $username, $password, $age, $role, $profile, $email, $webpage)
    {
        $this->pdo->exec("UPDATE students SET Name='" . $name . "',username='" . $username . "',password='" . $password . "',age=" . $age . ",role='" . $role . "',profile='" . $profile . "',email='" . $email . "',webpage='" . $webpage . "' where ID=" . $id);

    }
}

?>

