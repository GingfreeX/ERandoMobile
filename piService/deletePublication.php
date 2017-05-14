<?php
require_once('Config1.php');

$pubId = $_GET['pubId'];
$sql = "DELETE FROM `publication` WHERE id = ".$pubId;
if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>";
}

