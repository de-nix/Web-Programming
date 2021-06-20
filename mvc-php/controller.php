<?php

require_once 'model.php';
require_once 'view.php';

class Controller
{
    private $view;
    private $model;	

    public function __construct(){
    	$this->model = new Model ();
        $this->view = new View();
    }

    public function service() {
	   if (isset($_GET['action']) && !empty($_GET['action'])) {
            if ($_GET['action'] == "getUser") {
   	            $this->getUser($_GET['user']);
            } else if ($_GET['action'] == "insertUser") {
   	            $this->insertUser($_GET['id'],$_GET['name'],$_GET['username'],$_GET['password'],$_GET['age'],$_GET['role'],$_GET['profile'],$_GET['email'],$_GET['webpage']);
            } else if ($_GET['action'] == "updateUser") {
   	            $this->updateUser($_GET['idNew'],$_GET['nameNew'],$_GET['usernameNew'],$_GET['passwordNew'],$_GET['ageNew'],$_GET['roleNew'],$_GET['profileNew'],$_GET['emailNew'],$_GET['webpageNew']);
            } else if ($_GET['action'] == "deleteUser") {
   	            $this->deleteUser($_GET['delete']);
            } else if ($_GET['action'] == "filterUser") {
   	            $this->getUsersByRole($_GET['filter']);
            }
            else {
                $this->getAllUsers();
            } 
	   }
    }

     public function insertUser($id, $name,$username, $password,$age, $role,$profile, $email,$webpage) {
    	   $this->model->insertUser($id, $name,$username, $password,$age, $role,$profile, $email,$webpage);
        }
     public function getUser($user) {
           $student = $this->model->getUser($user);
           return $this->view->output($student);
        }
    public function deleteUser($id) {
       $this->model->deleteUser($id);
    }

    public function updateUser($id, $name, $username, $password, $age, $role, $profile, $email, $webpage) {
	   $this->model->updateUser($id, $name,$username, $password,$age, $role,$profile, $email,$webpage);
    }


    public function getAllUsers() {
       $studs = $this->model->getAllUsers();
       return $this->view->output($studs);
    }
    public function getUsersByRole($filter) {
       $studs = $this->model->getUsersByRole($filter);
       return $this->view->output($studs);
    }
}

$controller = new Controller();
$controller->service();

?>
