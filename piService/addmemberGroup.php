<?php
require_once('Config1.php');

$userId = $_GET['userId'];
$groupId = $_GET['groupId'];

$sql = "insert into users_groups(member_id,groupe_id)values ('$userId','$groupId')";
if (mysqli_query($conn, $sql)) {

    echo "success";
} else {
    echo "Error: " .$sql;
}

mysqli_close($conn);
?>

