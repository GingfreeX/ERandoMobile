<?php
require_once('../Config1.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/');
$titre=$_GET['titre'];
$descritpion=$_GET['descritpion'];
$prix=$_GET['prix'];
$date=$_GET['date'];
$type=$_GET['type'];
$idMembre=$_GET['idMembre'];

$imageName=$_POST['image'];
$img=base64_decode($imageName);
$uid=uniqid();
$file = $uid . '.jpg';

$sql = "INSERT INTO produit ( titre, description, prix,dateAdd,imageName,type,idMembre)
VALUES ( '$titre','$descritpion','$prix','$date','$file','$type','$idMembre')";

if (mysqli_query($conn, $sql)) {
	file_put_contents($file, $img);
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>