<?php
require_once('../Config1.php');

$id = $_GET['id'];
$message=$_GET['message'];
 

$sql = "update temoignage set message='".$message."' where id = ".$id;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql;
}

