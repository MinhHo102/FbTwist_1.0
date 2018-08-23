<?php
session_start();
// include('config.php');
// include('session.php');
// echo "<table id='selectable' style='border: solid 1px black;'>";
// echo "<tr>
//         <th>UserId</th>
//         <th>Type</th>
//         <th>Name</th>
//         <th>Bobas</th>
//         <th>Jellys</th>
//         <th>Size</th>
//         <th>Tapioca</th>
//         <th>Date</th>
//         </tr>";

class TableRows extends RecursiveIteratorIterator {
    function __construct($it) {
        parent::__construct($it, self::LEAVES_ONLY);
    }

    function current() {
        return "<td style='width:150px;border:1px solid black;'>" . parent::current(). "</td>";
    }

    function beginChildren() {
        echo "<tr>";
    }

    function endChildren() {
        echo "</tr>" . "\n";
    }
}
?>


<!doctype html>
<html lang="en">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Home - Select a Date Range</title>
  <link rel="stylesheet" href="style.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    var dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 2
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#to" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 2
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });

    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }

      return date;
    }
  } );
  </script>
  <!-- /*<style>*/ -->
  <!-- /*#feedback { font-size: 1.4em; }*/
  /*#selectable .ui-selecting { background: #FECA40; }*/
  /*#selectable .ui-selected { background: #F39814; color: white; }*/
  /*#selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }*/
  /*#selectable li { margin: 3px; padding: 0.4em; font-size: 1.4em; height: 18px; }*/ -->
  <!-- </style> -->
  <style>
  table {
    /*background-color: #fd9;*/
    /*border-collapse: collapse;
    display:block;
    table-layout: fixed;*/
  }
  table {
      background-color: #fd9;
      table-layout: fixed;
  }
  tbody {
    background-color: #fff;
    height:500px;
    overflow: auto;
}
  td {
      padding: 3px 10px;
  }
  thead > tr {
    padding-bottom: .5em;
    padding-top: .5em;
  }
  thead > tr, tbody {
    display: block;
  }
  td {
    white-space: pre-wrap;
  }
  td:empty {
    background: grey;
  }
  /*tbody {
    display: table-row-group;
    display: block;
  }*/


  /*td, th {
  text-overflow: ellipsis;
  overflow:hidden;
}*/
  /*thead > tr {display: block;}*/
  /*thead > td, tbody{ display:block;}*/
  /*thead > tbody { display:block;}*/
  </style>
  <script>
  $( function() {
    $( "#selectable" ).selectable();
  } );
  </script>
</head>
<body>

<h1>Welcome <//?php echo $_SESSION['id'];?></h1>
<div id="searchbar">
  <form method="post" action="" name="search">
    <label for="from">From</label>
    <input type="text" id="from" name="from">
    <label for="to">To</label>
    <input type="text" id="to" name="to">

    <input type="submit" class="button" name="searchButton" value="search">
  </form>
</div>
<br><br>
<table id='selectable' style='border: solid 1px black; border-collapse: collapse;'>
  <thead>
    <tr>
      <!-- columns don't allign with row values, so this is my dumb solution "white-space: pre-wrap" -->
      <td>Item #               </td>
      <td>UserId                  </td>
      <td>Type               </td>
      <td>Name           </td>
      <td>TeaType      </td>
      <td>SmoothieType</td>
      <td>Bobas                </td>
      <td>Jellys                   </td>
      <td>Size                 </td>
      <td>Tapioca  </td>
      <td>Protein       </td>
      <td>Date        </td>
  </tr>
</thead>
<tbody>
  <tr>
    <?php
    if (!empty($_POST['searchButton'])) {
        $from = $_POST['from'];
        $to = $_POST['to'];
        $from = date('Y-m-d H:i:s', strtotime($from));
        $to = date('Y-m-d H:i:s', strtotime($to));


    $servername = "localhost";
    $username = "root";//change this to relevant user
    $password = "root";
    $dbname = "mytwist";
     try {
        $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare("SELECT id, userid, type, name, teatype, smoothietype, boba, jelly, size, tapioca, protein, date FROM ordersPost
        WHERE date BETWEEN '$from' AND '$to'");
        $stmt->execute();

        // set the resulting array to associative
        $result = $stmt->setFetchMode(PDO::FETCH_ASSOC);
        foreach(new TableRows(new RecursiveArrayIterator($stmt->fetchAll())) as $k=>$v) {
            echo $v;
        }
    }
    catch(PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
    $conn = null;
    // echo "</table>";
    }

    ?>
  </tr>
</tbody>
</table>
</body>
</html>



<h4><a href="logout.php">Logout</a></h4>

<?php if (!empty($from)) echo $from ?>
<?php if (!empty($to)) echo $to ?>
<!-- </body> -->
