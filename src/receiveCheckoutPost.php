<?php
$database_hostname = "localhost";
$database_user = "customer";
$database_password = "customer714";
$database_name = "mytwist";

//Can also set this function through INI settings
date_default_timezone_set('America/Chicago');
$current_timedate = date('Y-m-d H:i:s');
try{
  $database_connection = new PDO("mysql:host=$database_hostname;dbname=$database_name",$database_user,$database_password);
  $database_connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}catch(PDOException $e){
  print_r('Connection failed: ' . $e->getMessage());
}
//check current hour, if it's outside store opening times, refuse the insert and send back
//appropriate message, $hour is 24H format
//Allowable times, 11am - 9pm ~ 11,
$date = strtotime($current_timedate);
$hour = date('G', $date);
// $hour = 10; for testing
if ($hour < 11 || ($hour > 20))
  print_r('Your order cannot be received; Mytwist is closing soon');
else {
    $foo = file_get_contents('php://input');
    $result = json_decode($foo, true);
    // print_r($result);

    $result = is_array($result) ? $result : array($result);
    // print_r($result);
    // $result['datetime'] = $current_timedate;
    // print_r($current_timedate);
    // var_dump($result);
    // print_r($result);
    // foreach ($result as $key => $value) {
    //     echo $value["name"] . ", " . $value["TeaType"] . "<br>";
    //   }
    // $bobas = '';
    // $jelly = '';
    // foreach ($result as $k){
    //   foreach ($k['boba'] as $value) {
    //     $bobas = implode(", ", $k['boba']);
    //     var_dump($bobas);
    //   }
    //   foreach ($k['jelly'] as $value) {
    //     $jelly = implode(", ", $k['jelly']);
    //     var_dump($jelly);
    //   }
      // if (is_array($result)) {
      //   foreach ($result as $value) {
      //     var_dump($value);
      //   }
      // }
      // else var_dump($result);

    $statement = $database_connection->prepare("insert into ordersPost(id, userid, type, name, teatype, smoothietype, boba, jelly, size, tapioca, protein, date)
     values(?,?,?,?,?,?,?,?,?,?,?,?)");


    foreach ($result as $row) {
    $bobas = '';
    $jelly = '';
    // we bindParam each value corresponding to structure of our table in our database
    $statement->bindParam(1, $row['id']);
    $statement->bindParam(2, $row['userid']);
    $statement->bindParam(3, $row['type']);
    $statement->bindParam(4, $row['name']);
    $statement->bindParam(5, $row['TeaType']);
    $statement->bindParam(6, $row['SmoothieType']);
    //implode will concatenate the array values, separating them by the first parameter
    // var_dump($row['boba']);
    if (isset($row['boba']) && is_array($row['boba'])){
      foreach ($row['boba'] as $bobaValues) {
        $bobas = implode(", ", $row['boba']);
      }
    }
    // var_dump($bobas);
    $statement->bindParam(7, $bobas);
    // var_dump($row['jelly']);
    if (isset($row['jelly']) && is_array($row['jelly'])){
      foreach ($row['jelly'] as $jellyValues) {
        $jelly = implode(", ", $row['jelly']);
      }
    }
    // var_dump($jelly);
    $statement->bindParam(8, $jelly);
    $statement->bindParam(9, $row['size']);
    $statement->bindParam(10, $row['tapioca']);
    $statement->bindParam(11, $row['protein']);
    //we bind a final datetime variable during runtime
    $statement->bindParam(12, $current_timedate);
    $statement->execute();
    }
    print_r('Order Successfully Submitted');
}
?>
