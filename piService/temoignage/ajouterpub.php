<?php
require_once('../Config1.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/temoignage/images/');
$Imagecode=$_POST['Image']; // parameter
$img=base64_decode($Imagecode);
$uid=uniqid();
$file = UPLOAD_DIR . $uid . '.jpg';
$success = file_put_contents($file, $img);
$i = $uid.'.jpg';
$id=$_POST['iduser'];
$description=$_POST['description'];
$type=$_POST['type'];

$datepub = date('Y-m-d');
$sql = "INSERT INTO publication_e (user_id,description,image,section,datepub)
VALUES ('$id','$description','$i','$type','$datepub')";

if (mysqli_query($conn, $sql)) {
} else {
    echo "success";
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>