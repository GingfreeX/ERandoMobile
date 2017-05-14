<?php
require_once('../Config1.php');
$idProduit=$_GET['idP'];
$idMembre=$_GET['idM'];



$sql = "select id from productlike where idProduit=$idProduit AND idMembre=$idMembre";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    echo $result->num_rows;
         
} else {
    echo 0;
}
$conn->close();

?>