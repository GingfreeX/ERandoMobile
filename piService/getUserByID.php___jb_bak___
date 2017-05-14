<?php
require_once 'config.php';
$id = $_GET['id'];

$con = new Config();
$req = "SELECT * FROM member where id=:id ";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':id',$id);
$st->execute();

$json = new SimpleXMLElement('<xml/>');
while($row= $st->fetch()) {
    $mydata = $json->addChild('user');
    $mydata->addChild('id', $row['id']);
    $mydata->addChild('username', $row['username']);
    $mydata->addChild('email', $row['email']);
    $mydata->addChild('age', $row['age']);
    $mydata->addChild('prenom', $row['prenom']);
    $mydata->addChild('mobile_number', $row['mobile_number']);
    $mydata->addChild('profile_pic', $row['profile_pic']);

}
echo (json_encode($json));