<?php
require_once('../Config1.php');
$idMembre=$_GET['idMembre'];
$type=$_GET['type'];



$sql = "select id from subscribers where idMembre=$idMembre AND type='$type'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    echo $result->num_rows;         
} else {
    echo 0;
}
$conn->close();

?>