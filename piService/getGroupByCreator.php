<?php

require_once('Config1.php');


$userId = $_GET['userId'];

$sql = "SELECT * FROM groupe where id_createur = " .$userId;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
// output data of each row
while ($row = $result->fetch_assoc()) {
    $mydata = $json->addChild('groupe');
    $mydata->addChild('id', $row['id']);
    $mydata->addChild('description', $row['description']);
    $mydata->addChild('id_createur', $row['id_createur']);
}

echo (json_encode($json));
