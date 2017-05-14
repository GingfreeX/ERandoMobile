<?php

require_once('Config1.php');


$groupId = $_GET['groupId'];

$sql = "SELECT * FROM groupe where id = " .$groupId;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
// output data of each row
while ($row = $result->fetch_assoc()) {
    $mydata = $json->addChild('groupe');
    $mydata->addChild('id', $row['id']);
        $mydata->addChild('nom', $row['nom']);
    $mydata->addChild('description', $row['description']);
    $mydata->addChild('photoCouverture', $row['photoCouverture']);
    $mydata->addChild('id_createur', $row['id_createur']);
    $mydata->addChild('date_creation', $row['date_creation']);
}

echo (json_encode($json));
