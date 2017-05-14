<?php

require_once('Config1.php');


$groupId = $_GET['groupId'];

$sql = "SELECT member_id FROM users_groups where groupe_id = " . $groupId;
$memid = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');

// output data of each row
while ($row = $memid->fetch_assoc()) {
    $sql1 = "SELECT * FROM member where id = " . $row['member_id'] . " ";
    $result = $conn->query($sql1);
// output data of each row
    while ($row1 = $result->fetch_assoc()) {
        $mydata = $json->addChild('users');
        $mydata->addChild('id', $row1['id']);
        $mydata->addChild('username', $row1['username']);
        $mydata->addChild('email', $row1['email']);
        $mydata->addChild('age', $row1['age']);
        $mydata->addChild('prenom', $row1['prenom']);
        $mydata->addChild('nom', $row1['nom']);
        $mydata->addChild('location', $row1['location']);
        $mydata->addChild('profile_pic', $row1['profile_pic']);
    }
}

echo (json_encode($json));





