<?php
require_once('../Config1.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/');
$idMembre=$_GET['idMembre'];
$type=$_GET['type'];
$email=$_GET['email'];


$sql = "INSERT INTO subscribers ( idMembre, type, emailMembre)
VALUES ( '$idMembre','$type','$email')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>