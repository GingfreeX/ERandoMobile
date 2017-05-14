<?php
require_once('../Config1.php');


$id=$_GET['idMembre'];
$type=$_GET['type'];
$sql = "delete from subscribers where type='$type' AND idMembre=".$id;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>