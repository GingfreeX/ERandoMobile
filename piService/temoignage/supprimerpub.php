<?php 
require_once('../Config1.php');

$id=$_GET['id'];
$sql = "DELETE FROM `publication_e` WHERE id = ".$id;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>";

}
?>