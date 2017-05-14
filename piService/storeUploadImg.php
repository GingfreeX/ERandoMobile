<?php
require_once('Config1.php');

$userId = $_GET['userId'];
$groupId = $_GET['groupId'];
$datepub = date("Y-m-d H:i:s"); 
$imageName = $_POST['image'];
$img=base64_decode($imageName);
$uid=uniqid();
$file = $uid . '.png';
$sql = "INSERT INTO `image`(`name`, `id_user`, `id_groupe`, `date_publication`) VALUES ('$file','$userId','$groupId','$datepub')";
if (mysqli_query($conn, $sql)) {
file_put_contents($file, $img);
    echo "success";
} else {
    echo "Error: ".$sql;
}
mysqli_close($conn);
?>

