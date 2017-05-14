<?php
require_once 'config.php';

define('UPLOAD_DIR',$_SERVER['DOCUMENT_ROOT'].'/piService/images/');
$Imagecode=$_POST['Image']; // parameter
$img=base64_decode($Imagecode);
$uid=uniqid();
$file = UPLOAD_DIR . $uid . '.jpg';
$success = file_put_contents($file, $img);
$i = $uid.'.jpg';
$con = new Config();

$req = "UPDATE member SET profile_pic=:profile_pic where email=:email";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':profile_pic',$i);
$st->bindParam(':email',$_POST['email']);
$st->execute();




?>

