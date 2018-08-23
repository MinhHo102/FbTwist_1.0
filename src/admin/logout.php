<?php
ob_start();
// include('config.php');
$session_id='';
$_SESSION['id']='';
// remove all session variables
// session_unset();

// destroy the session
// session_destroy();
// if(empty($session_id) && empty($_SESSION['id']))
// {
// $url=BASE_URL.'index.php';
// header("Location: $url");
header("Location: ../index.php");
exit();
// echo "<script>window.location='$url'</script>";
// }
?>
