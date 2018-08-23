<?php

class ClassUser
{
/* User Login */
public function userLogin($username,$password) {
  try {
    $db = getDB();
    $hash_password= hash('sha256', $password); //Password encryption
    $stmt = $db->prepare("SELECT id FROM userlogin WHERE (username=:username) AND password=:hash_password");
    $stmt->bindParam("username", $username,PDO::PARAM_STR) ;
    $stmt->bindParam("hash_password", $hash_password,PDO::PARAM_STR) ;
    $stmt->execute();
    $count=$stmt->rowCount();
    $data=$stmt->fetch(PDO::FETCH_OBJ);
    $db = null;
      if($count) {
        $_SESSION['id']=$data->id; // Storing user session value
        return true;
      }
      else {
        return false;
      }
    }
  catch(PDOException $e) {
    echo '{"error":{"text":'. $e->getMessage() .'}}';
  }
}

/* User Registration */
public function userRegistration($username,$password) {
  $usertype = "ADMIN";
  try {
    $db = getDB();
    $st = $db->prepare("SELECT id FROM userlogin WHERE username=:username");
    $st->bindParam("username", $username,PDO::PARAM_STR);
    $st->execute();
    $count=$st->rowCount();
    if($count < 1) {

      $stmt = $db->prepare("insert into userlogin(username,usertype,password) values(?,?,?)");
      $stmt->bindParam(1, $username, PDO::PARAM_STR);
      $stmt->bindParam(2, $usertype, PDO::PARAM_STR);
      $hash_password= hash('sha256', $password); //Password encryption
      $stmt->bindParam(3, $hash_password, PDO::PARAM_STR);

      $stmt->execute();
      $id=$db->lastInsertId(); // Last inserted row id
      $db = null;
      $_SESSION['id']=$id;
      return true;
    }
    else {
      $db = null;
      return false;
    }
  }

catch(PDOException $e) {
  echo $e->getMessage();
  }
}

}
?>
