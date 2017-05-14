<?php

require_once('Config1.php');


$groupId = $_GET['groupId'];

$sql = "select * from image where id_groupe = " . $groupId;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');

// output data of each row
// output data of each row
    while ($row1 = $result->fetch_assoc()) {
        $mydata = $json->addChild('images');
        $mydata->addChild('id', $row1['id']);
        $mydata->addChild('name', $row1['name']);
        $mydata->addChild('id_user', $row1['id_user']);
        $mydata->addChild('id_groupe', $row1['id_groupe']);
        $mydata->addChild('date_publication', $row1['date_publication']);
       
    }


echo (json_encode($json));





