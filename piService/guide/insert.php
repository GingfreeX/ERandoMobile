<?php
require_once('../Config1.php');
define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/images/');
$Imagecode=$_POST['Image']; // parameter
$img=base64_decode($Imagecode);
$uid=uniqid();
$file = UPLOAD_DIR . $uid . '.jpg';

$i = $uid.'.jpg';
$titre=$_POST['titre'];
$description=$_POST['description'];
$destination=$_POST['destination'];
$date=$_POST['date'];
$prix=$_POST['prix'];

$nbr_places=$_POST['nbr_places'];
$point_depart=$_POST['point_depart'];
$type=$_POST['type'];
$niveau=$_POST['niveau'];
$age_min=$_POST['age_min'];
$moyen_transport=$_POST['transport'];




$sql = "INSERT INTO randonne (titre,description,destination,date,prix,image,nbr_places,point_depart,type,niveau,age_min,moyen_transport)
VALUES ( '$titre','$description','$destination','$date','$prix','$i','$nbr_places','$point_depart','$type','$niveau','$age_min','$moyen_transport')";

if (mysqli_query($conn, $sql)) {
	 file_put_contents($file, $img);
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>