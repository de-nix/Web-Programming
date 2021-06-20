<?php

require_once 'DBUtils.php';
require_once 'user.php';

class Model {
	private $db;

	public function __construct() {
		$this->db = new DBUtils ();
	}

	public function getUser($user) {
		$resultset = $this->db->selectUser($user);
	    //var_dump($resultset);
        //$student = new Student($resultset[0].id, $resultset[0].name, $resultset[0].username,$resultset[0].password, $resultset[0].age, $resultset[0].role,$resultset[0].profile, $resultset[0].email, $resultset[0].webpage);
        return $resultset;
	}
	public function insertUser($id, $name, $username, $password, $age, $role, $profile, $email, $webpage) {
		$this->db->insert($id, $name,$username, $password,$age, $role,$profile, $email,$webpage);
	    //var_dump($resultset);
//        $student = new Student($resultset[0]['id'], $resultset[0]['name'], $resultset[0]['age']);
//        return $student;
	}
	public function updateUser($id, $name, $username, $password, $age, $role, $profile, $email, $webpage) {
		$this->db->update($id, $name,$username, $password,$age, $role,$profile, $email,$webpage);
	    //var_dump($resultset);
//        $student = new Student($resultset[0]['id'], $resultset[0]['name'], $resultset[0]['password'], $resultset[0]['group_id']);
//        return $student;
	}
	public function deleteUser($id) {
		$this->db->delete($id);
	    //var_dump($resultset);
//        $student = new Student($resultset[0]['id'], $resultset[0]['name'], $resultset[0]['password'], $resultset[0]['group_id']);
//        return $student;
	}




    public function getAllUsers() {
        $resultset = $this->db->selectAllUsers();

        return $resultset;
    }

    public function getUsersByRole($role) {
        $resultset = $this->db->selectUsersByRole($role);

        return $resultset;
    }



}

?>
