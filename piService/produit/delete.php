<?php
require_once('../Config1.php');


$id=$_GET['id'];
$sql = "delete from produit where id=".$id;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>