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
            if ($_POST['action'] == "login") {
                $login =$this->authenticateUser();
                if ($login != null) {
                    $_SESSION["user"] = $login;

                    echo "<script>window.location.href = '../web/index.php'</script>";
                    }

            }else if ($_POST['action'] == "addAssets") {
                  $this->addAssets();
            }else if ($_POST['action'] == "getOwn") {
                echo  $this->getOwn();
            }else if ($_POST['action'] == "getOther") {
                 echo  $this->getOther();
            }else if ($_POST['action'] == "updatePost") {
                  $this->updatePost();
            }else if ($_POST['action'] == "addPost") {
                 echo $this->addPost();
            }
        }else if (isset($_GET['action']) && !empty($_GET['action'])){
            if ($_GET['action'] == "getAssets") {
                echo $this->getUserAssets();}
        }

    }


    public function authenticateUser(){
        if( isset($_POST["username"])){
            return $_POST["username"];
        }
        return null;
    }


    private function updatePost()
    {
        $this->db->updatePost($_POST["id"],$_POST["user"],$_POST["topic"],$_POST["text"],$_POST["date"]);
    }


    private function addPost()
    {
        return "<script>console.log(dtgfhvgjk)</script>";
//        echo "<script>console.log(".$_POST["user"].")</script>";
//        echo "<script>console.log(".$_POST["topic"].")</script>";
//        echo "<script>console.log(".$_POST["text"].")</script>";
//        echo "<script>console.log(".$_POST["date"].")</script>";
       // echo "<script>console.log(".$_POST["id"].")</script>";
        //$this->db->addPost($_POST["id"],$_POST["user"],$_POST["topic"],$_POST["text"],$_POST["date"]);
    }


}

$controller = new Controller();
$controller->redirectToAction();