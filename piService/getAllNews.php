<?php
require_once 'config.php';


$con = new Config();
$req = "SELECT * FROM publication_e ";
$st = $con->getPDO()->prepare($req);
$st->execute();

$json = new SimpleXMLElement('<xml/>');
while($row= $st->fetch()) {
    $mydata = $json->addChild('publication');
    $mydata->addChild('id', $row['id']);
    $mydata->addChild('user_id', $row['user_id']);
    $mydata->addChild('description', $row['description']);
    $mydata->addChild('section', $row['section']);
    $mydata->addChild('datepub', $row['datepub']);
}
echo (json_encode($json));

