<?php

require_once('Config1.php');


$userId = $_GET['userId'];

$sql = "SELECT * FROM member where id= " .$userId;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
    // output data of each row
    while ($row = $result->fetch_assoc()) {
        $mydata = $json->addChild('users');
        $mydata->addChild('id', $row['id']);
        $mydata->addChild('username', $row['username']);
        $mydata->addChild('email', $row['email']);
        $mydata->addChild('age', $row['age']);
        $mydata->addChild('nom', $row['nom']);
        $mydata->addChild('prenom', $row['prenom']);
        $mydata->addChild('location', $row['location']);
        $mydata->addChild('liste_amis', $row['liste_amis']);
        $mydata->addChild('profile_pic', $row['profile_pic']);
    }

    echo (json_encode($json));





