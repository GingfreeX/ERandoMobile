<?php
require_once('../Config1.php');

$idProduit=$_GET['idP'];
$idMembre=$_GET['idM'];
$sql = "delete from productlike where idProduit=$idProduit AND idMembre=$idMembre";
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>