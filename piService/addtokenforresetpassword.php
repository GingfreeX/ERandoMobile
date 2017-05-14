<?php
require_once 'config.php';
$id = $_GET['email'];
$token = $_GET['token'];
$con = new Config();
if($id==""){
    echo "error";
}
$req2 = "select * from member where email=:email";
$st1 = $con->getPDO()->prepare($req2);
$st1->bindParam(':email',$id);
$st1->execute();
$row = $st1->rowCount();
if($row == 1){
    $req = "update member set tokenForPassword=:tokenForPassword where email=:email";
    $st = $con->getPDO()->prepare($req);
    $st->bindParam(':tokenForPassword',$token);
    $st->bindParam(':email',$id);
    if($st->execute()){
        echo "success";
    }else{
        echo "error";
    }
}else{
    echo "error";
}
