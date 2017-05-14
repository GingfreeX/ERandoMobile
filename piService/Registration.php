<?php
require_once 'config.php';
require 'BCrypt.php';

$nom = $_GET['nom'];
$email = $_GET['email'];
$password =  $_GET['password'];
$numtel =  $_GET['numtel'];
$enabled = 0 ;
$con = new Config();
$bcrypt = new Bcrypt(13);
$pw = $bcrypt->hash($password);
$req = "INSERT INTO member SET username=:username , username_canonical=:username_canonical,
 email=:email,	email_canonical=:email_canonical,enabled=:enabled,password=:password,mobile_number=:mobile_number";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':username',$nom);
$st->bindParam(':username_canonical',$nom);
$st->bindParam(':email',$email);
$st->bindParam(':email_canonical',$email);
$st->bindParam(':enabled',$enabled);
$st->bindParam(':password',$pw);
$st->bindParam(':mobile_number',$numtel);
if($st->execute()){
    echo "success";
}else{
    echo "error";
}