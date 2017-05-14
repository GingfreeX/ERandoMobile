<?php
require_once 'config.php';
require'BCrypt.php';
$login= $_GET['login'];
$password = $_GET['password'];
$con = new Config();
$req = "SELECT * FROM member where email=:email";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':email',$login);
$st->execute();
$row = $st->rowCount();
$row1= $st->fetch();


if($row == 1){

    $bcrypt = new Bcrypt(13);
    $isGood = $bcrypt->verify($password, $row1[7]);
    if($isGood==1){
        echo 'success';
    }
}




