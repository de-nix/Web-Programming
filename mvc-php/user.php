<?php

class User implements JsonSerializable {
	private $id;
	private $name;
	private $username;
	private $password;
	private $age;
	private $role;
	private $webpage;
	private $profile;
	private $email;

	public function __construct($id, $name,$username, $password,$age,$role,$profile, $email, $webpage) {
		$this->id = $id;
		$this->name = $name;
		$this->age = $age;
		$this->username = $username;
		$this->password = $password;
		$this->role = $role;
		$this->profile = $profile;
		$this->email = $email;
		$this->webpage = $webpage;
	}

	public function getId() {
		return $this->id;
	}
	public function getName() {
		return $this->name;
	}
	public function getAge() {
		return $this->age;
	}

	public function jsonSerialize() {
        $vars = get_object_vars($this);
        return $vars;
    }
}

?>
