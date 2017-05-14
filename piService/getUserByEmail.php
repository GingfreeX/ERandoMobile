<?php
require_once 'config.php';
$email = $_GET['email'];

$con = new Config();
$req = "SELECT * FROM member where email=:email ";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':email',$email);
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
    $mydata->addChild('liste_amis', $row['liste_amis']);
}
echo (json_encode($json));


