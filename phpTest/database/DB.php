<?php
require_once '../model/Topic.php';
require_once '../model/Post.php';

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


    public function subscribe(Post $asset){
        $this->pdo->exec("insert into asset(name,description,value,userId) values (
            '" . $asset->getDate() . "','"
            . $asset->getUser() . "',"
            . $asset->getText() . ","
            . $asset->getTopicId() . ")");
    }
    public function updateAsset(Post $asset){
        $this->pdo->exec("update asset
             set name = '" . $asset->getDate() . "', set description = '"
            . $asset->getUser() . "', set value = "
            . $asset->getText() . ", set userID = "
            . $asset->getTopicId() . " where id = ". $asset->getId());
    }
    public function deleteAsset($id){
        $this->pdo->exec("delete from asset where id = " . $id);
    }

    public function getUserAssets($id){
        $resultData = $this->pdo->query("select * from channels where ownerid = ". $id);
        return $resultData->fetchAll(PDO::FETCH_OBJ);
    }


    public function updatePost($id, $user, $topic, $text, $date)
    {

        $this->pdo->exec("update posts set user = '" . $user . "', topicid = " . $topic . ", text = '" . $text . "', date = " . $date . " where id = " . $id);
    }

    public function addPost($id, $user, $topic, $text, $date)
    {
        $resultData = $this->pdo->query("select * from topics where name = '".$topic."'");
        $result = $resultData->fetch();
        if ($result) {

            //$this->pdo->exec("insert into posts set user = '" . $user . "', topicid = " . $result["topicid"] . ", text = '" . $text . "', date = " . $date . " where id = " . $id);
        }
        else{
           // $this->pdo->exec("insert into topics(name) values(".$topic.")");
           // $this->pdo->exec("insert into posts set user = '" . $user . "', topicid = " . $topic . ", text = '" . $text . "', date = " . $date . " where id = " . $id);

        }

    }


}
?>