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
$idpu=$_POST['id'];

$datepub = date('Y-m-d');
$sql = "UPDATE publication_e SET user_id='$id',description='$description',image='$i',section='$type',datepub='$datepub'
where id='$idpu'";

if (mysqli_query($conn, $sql)) {
	echo "success";
} else {
    
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>