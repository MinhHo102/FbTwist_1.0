<?php
ob_start();
include("config.php");
include('ClassUser.php');

$ClassUser = new ClassUser();
// $errorMsgReg='';
$errorMsgLogin='';
/* Login Form */
if (!empty($_POST['loginSubmit'])) {
  $usernameEmail=$_POST['usernameEmail'];
  $password=$_POST['password'];
  if(strlen(trim($usernameEmail))>1 && strlen(trim($password))>1) {
    $id=$ClassUser->userLogin($usernameEmail,$password);
    if($id) {
      $errorMsgLogin="success login";
      header("Location: admin/home.php");
      exit();
    }
    else {
      $errorMsgLogin="Please check login details.";
    }
  }
  else {
    $errorMsgLogin="Please check login details2.";
  }
}
///
/* Signup Form */
// if (!empty($_POST['signupSubmit'])) {
// $username=$_POST['usernameReg'];
// $password=$_POST['passwordReg'];
// /* Regular expression check */
// $username_check = preg_match('~^[A-Za-z0-9_]{3,20}$~i', $username);
// $password_check = preg_match('~^[A-Za-z0-9!@#$%^&*()_]{6,20}$~i', $password);
// //
// if($username_check && $password_check) {
// $id=$ClassUser->userRegistration($username,$password);
//   if($id) {
//     $url=BASE_URL.'home.php';
//     header("Location: $url"); // Page redirecting to home.php
//     }
//   else {
//     $errorMsgReg="Username or Email already exists: ".$id;
//     }
//   }
// }
// ob_end_clean();
?>
	<link rel="stylesheet" type="text/css" href="style.css">

    <div id="login">
      <h3>Login</h3>
        <form method="post" action="" name="login">
          <label>Username or Email</label>
          <input type="text" name="usernameEmail" autocomplete="off" />
          <label>Password</label>
          <input type="password" name="password" autocomplete="off"/>
          <div class="errorMsg"><?php echo $errorMsgLogin; ?></div>
          <input type="submit" class="button" name="loginSubmit" value="Login">
        </form>
      </div>
      <!-- <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
      <div id="signup">
        <h3>Registration</h3>
        <form method="post" action="" name="signup">
          <label>Username Or Email</label>
          <input type="text" name="usernameReg" autocomplete="off" />
          <label>Password</label>
          <input type="password" name="passwordReg" autocomplete="off"/>
          <div class="errorMsg"><//?php echo $errorMsgReg; ?></div>
          <input type="submit" class="button" name="signupSubmit" value="Signup">
        </form>
      </div> -->
