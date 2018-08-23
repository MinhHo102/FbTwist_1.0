<?php
if(!empty($_SESSION['id']))
{
$session_id=$_SESSION['id'];
include('ClassUser.php');
$userClass = new ClassUser();
}
if(empty($session_id))
{
$url=BASE_URL.'index.php';
header("Location: $url");
}
?>
