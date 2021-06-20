<?php
require_once '../database/DB.php';


session_start();

class Controller
{
    private $db ;

    /**
     * Controller constructor.
     * @param $db
     */
    public function __construct()
    {
        $this->db = new DB();
        $_SESSION["user"] = null;
    }
    public function redirectToAction(){
        if (isset($_POST['action']) && !empty($_POST['action'])) {
            if ($_POST['action'] == "getAssets") {
               echo json_encode($this->getUserAssets());
            } else if ($_POST['action'] == "register") {
                $register = $this->insertUser();
                if ($register != null ){
                $_SESSION["user"] = $register;
                 echo "<script>window.location.href = '../web/index.php'</script>";
            }
            } else if ($_POST['action'] == "login") {
                $login =$this->authenticateUser();
                if ($login != null) {
                    $_SESSION["user"] = $login;

                    echo "<script>window.location.href = '../web/index.php'</script>";
                    }

            }else if ($_POST['action'] == "addAssets") {
                  $this->addAssets();
            }
        }else if (isset($_GET['action']) && !empty($_GET['action'])){
            if ($_GET['action'] == "getAssets") {
                echo $this->getUserAssets();}
        }

    }

    public function addAssets(){

        $elems = json_decode($_POST["arr"]);
        foreach ($elems as $elem){
            $asset = new Asset();
            $asset->setName($elem->name);
            $asset->setDescription($elem->description);
            $asset->setValue($elem->value);
            $asset->setUserId($elem->userId);
            $this->db->insertAsset($asset);
        }

    }

    public function insertUser(){
        echo $_POST["username"];
        if( isset($_POST["username"]) and isset($_POST["password"])){
            $u = new User();
            $u->setUsername($_POST["username"]);
            $u->setPassword($_POST["password"]);
            return $this->db->insertUser($u);
        }
        return null;
    }
    public function authenticateUser(){
        if( isset($_POST["username"]) and isset($_POST["password"])){
            $u = new User();
            $u->setUsername($_POST["username"]);
            $u->setPassword($_POST["password"]);
            echo $u;

            return $this->db->authenticateUser($u);
        }
        return null;
    }

    public function getUserAssets(){
        if( isset($_GET["id"])){
            return json_encode($this->db->getUserAssets($_GET["id"]));
        }
        return null;
    }



}

$controller = new Controller();
$controller->redirectToAction();