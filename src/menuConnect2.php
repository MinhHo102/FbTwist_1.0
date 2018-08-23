<?php
	$database_hostname = "localhost";
	// $database_user = "normal";
	// $database_password = "password";
	$database_user = "customer";
	$database_password = "customer714";
	$database_name = "mytwist";

//charset is super important!!!!!
	try{
		$database_connection = new PDO("mysql:host=$database_hostname;dbname=$database_name;charset=UTF8",$database_user,$database_password);
		$database_connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

	}catch(PDOException $e){

		// die($e->getMessage());
		print_r($e->getMessage());
	}

  $query = "SELECT id, name, description FROM XSmoothie";
	$statement = $database_connection->prepare($query);
	$statement->execute();


	$userData_List = array();

	while($row=$statement->fetch(PDO::FETCH_ASSOC)){
		$userData_List[] = $row;
	}
	echo json_encode($userData_List);
	// print_r($userData_List);
	// var_dump($userData_List);
?>
