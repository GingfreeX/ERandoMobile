<?php
require_once 'config.php';


$nom = $_GET['nom'];
$prenom = $_GET['prenom'];
$age =  $_GET['age'];
$phonenum =  $_GET['mobile_number'];
$email =  $_GET['email'];


$con = new Config();

$req = "UPDATE member SET username=:username , username_canonical=:username_canonical,
 prenom=:prenom,age=:age,mobile_number=:mobile_number where email=:email";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':username',$nom);
$st->bindParam(':username_canonical',$nom);
$st->bindParam(':prenom',$prenom);
$st->bindParam(':age',$age);
$st->bindParam(':mobile_number',$phonenum);
$st->bindParam(':email',$email);

if($st->execute()){
    echo "success";
}else{
    echo "error";
}