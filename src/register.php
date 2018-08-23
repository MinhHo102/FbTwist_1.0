<?php
$host = 'localhost';
$db = 'mytwist';
$user = 'normal';
$pass = 'password';
$charset = 'utf8mb4';

$phone = $_POST['phone'];
$password = $_POST['pass'];

$opt = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

$dsn = "mysql:host=$host;port=8888;dbname=$db;charset=$charset";
try {
	$pdo = new PDO($dsn, $user, $pass, $opt);
	if ($pdo) echo "connection to db is successful\t";
}catch (PDOException $e){
	echo $e->getMessage();
}
try {
	$query = $pdo->prepare("INSERT INTO user(phone, password) VALUES(:phone, :password)");
	$query->bindParam(':phone', $phone);
	$query->bindParam(':password', $password);
	$query->execute();
	echo "i think execute successful";
}catch(PDOException $ex) {
	echo "exception caught: " . $ex->getMessage();
}
?>
