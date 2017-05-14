<?php
require_once('../Config1.php');

//$id=$_GET['id'];
$iduser=$_GET['iduser'];
$message=$_GET['message'];
$sql = "INSERT INTO temoignage (iduser_id, message) VALUES ('$iduser', '$message')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>