<?php
require_once('../Config1.php');

$titre=$_GET['titre'];
$description=$_GET['description'];
$prix=$_GET['prix'];
$id=$_GET['id'];


$sql = "update produit set titre='$titre',description='$description',prix='$prix' where id = $id";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>