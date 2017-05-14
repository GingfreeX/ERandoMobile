<?php
require_once('Config1.php');

$groupId = $_GET['groupId'];
$userId = $_GET['userId'];

$sql = "delete from users_groups where member_id = ".$userId." and groupe_id = ".$groupId;

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>";
}

