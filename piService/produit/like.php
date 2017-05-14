<?php
require_once('../Config1.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/');
$idProduit=$_GET['idP'];
$idMembre=$_GET['idM'];


$sql = "INSERT INTO productlike ( idProduit, idMembre)
VALUES ( '$idProduit','$idMembre')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>