<?php
require_once 'config.php';
$id = $_GET['email'];
$token = $_GET['token'];
$con = new Config();
$req = "select * from  member  where email=:email";
$st = $con->getPDO()->prepare($req);

$st->bindParam(':email',$id);
$st->execute();
while($row = $st->fetch()){
    if($row['tokenForPassword']==$token){
      echo "success";
    }else{
        echo "error";
    }
}