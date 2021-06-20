<?php
require_once '../model/User.php';
require_once '../model/Asset.php';

class DB
{
    private $user = 'root';
    private $password = '';
    private $host = 'localhost';
    private $db = 'exam';
    private $pdo;

    /**
     * DB constructor.
     */
    public function __construct()
    {
        $opt = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES => false);
        $connection = "mysql:host=" . $this->host . ";dbname=" . $this->db;
        $this->pdo = new PDO($connection, $this->user,$this->password, $opt);
    }



    public function getUserAssets($id){
        $resultData = $this->pdo->query("select * from Files where userid = ". $id);
        return $resultData->fetchAll(PDO::FETCH_OBJ);

    }

    public function authenticateUser(User $param)
    {
        $resultData = $this->pdo->query("select * from users where username = '". $param->getUsername() . "' and 
        password = '" .$param->getPassword() . "'");
        return $resultData->fetchObject('User');
    }

    public function uniqueUser($param)
    {
        $resultData = $this->pdo->query("select * from users where username = '". $param . "' ");
        if ($resultData->fetch()) return false;
        return true;
    }



}
?>
