<?php
require_once 'config.php';
require 'BCrypt.php';

$password = $_GET['password'];
$email = $_GET['email'];
$bcrypt = new Bcrypt(13);
$pw = $bcrypt->hash($password);
$con = new Config();
$req = "update member set password=:password where email=:email";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':password',$pw);
$st->bindParam(':email',$email);
if($st->execute()){
    echo "success";
}else {
    echo "error";
}