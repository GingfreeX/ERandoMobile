<?php
require_once('Config1.php');

$userId = $_GET['userId'];
$nom = $_GET['nom'];
$des = $_GET['des'];
$date = date("Y-m-d H:i:s");
$sql = "INSERT INTO `groupe`(`nom`, `description`,`id_createur`, `date_creation`) VALUES ('$nom','$des','$userId','$date')";
if (mysqli_query($conn, $sql)) {

    echo "success";
} else {
    echo "Error: " .$sql;
}

mysqli_close($conn);
?>

