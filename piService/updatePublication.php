<?php
require_once('Config1.php');

$pubId = $_GET['pubId'];
$desc = $_GET['desc'];
$datepub = date("Y-m-d H:i:s"); 

$sql = "update publication set description='".$desc."',datepub ='".$datepub."' where id = ".$pubId;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql;
}

