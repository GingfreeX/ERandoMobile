<?php

require_once('Config1.php');


$groupId = $_GET['groupId'];

$sql = "SELECT * FROM publication where group_id = " . $groupId . " ORDER BY datepub DESC";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
    // output data of each row
    while ($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('publication');
        $mydata->addChild('id', $row['id']);
        $mydata->addChild('user_id', $row['user_id']);
        $mydata->addChild('group_id', $row['group_id']);
        $mydata->addChild('description', $row['description']);
        $mydata->addChild('datepub', $row['datepub']);
        $mydata->addChild('photo', $row['photo']);
        $mydata->addChild('nbrJaime', $row['nbrjaime']);
    }

    echo (json_encode($json));





