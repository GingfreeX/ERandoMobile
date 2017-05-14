<?php
require_once 'config.php';
$con = new Config();
$auid = $_GET['author_id'];
$rec = $_GET['addressee_id'];
$message = $_GET['messageText'];
$req = "insert into  belousovr_messages set	author_id=:author_id ,addressee_id=:addressee_id,messageText=:messageText";
$st = $con->getPDO()->prepare($req);
$st->bindParam('author_id',$auid);
$st->bindParam('addressee_id',$rec);
$st->bindParam('messageText',$message);
if($st->execute()){
    echo 'success';
}else{
    echo 'error';
}
