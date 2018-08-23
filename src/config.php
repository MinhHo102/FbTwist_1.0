<?php
session_start();
define('DatabaseServer', 'localhost');
define('DatabaseUser', 'root');
define('DatabasePass', 'root');
define('DatabaseName', 'phplogin');
define('BASE_URL', 'localhost:8888/'); // Eg. http://yourwebsite.com
function getDB()
{
  $dbhost = DatabaseServer;
  $dbuser = DatabaseUser;
  $dbpass = DatabasePass;
  $dbname = DatabaseName;
try {
  $dbConnection = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);
  $dbConnection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
return $dbConnection;
}
catch (PDOException $e) {
  echo 'Connection Failed: ' . $e->getMessage();
}
}
?>
