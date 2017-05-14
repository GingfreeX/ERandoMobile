<?php

require_once('Config1.php');


$groupId = $_GET['groupId'];

$sql = "select member_id from users_groups where groupe_id  = " . $groupId;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
// output data of each row
while ($row = $result->fetch_assoc()) {
    $mydata = $json->addChild('groupmembers');
    $mydata->addChild('member_id', $row['member_id']);
    
}

echo (json_encode($json));
