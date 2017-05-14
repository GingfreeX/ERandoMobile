<?php
require_once 'config.php';
$auid = $_GET['author_id'];
$rec = $_GET['addressee_id'];
$con = new Config();
$req = "select * from belousovr_messages where
(author_id =:author_id or author_id =:addressee_id)
  AND
(addressee_id =:author_id or addressee_id =:addressee_id)";
$st = $con->getPDO()->prepare($req);
$st->bindParam(':author_id',$auid);
$st->bindParam(':addressee_id',$rec);
$st->execute();
$json = new SimpleXMLElement('<xml/>');
while($row= $st->fetch()) {
    $mydata = $json->addChild('messages');
    $mydata->addChild('author_id', $row['author_id']);
    $mydata->addChild('addressee_id', $row['addressee_id']);
    $mydata->addChild('messageText', $row['messageText']);

}
echo (json_encode($json));
