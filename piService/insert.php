<?php
include ("Config.php");
$co = new Config();
$connection = $co->connect();

$nom = $_GET['nom'];
$description = $_GET['description'];
$sql = $connection->prepare("INSERT INTO groupe (nom,description) VALUES (? ,? )");
$sql->bindParam(1,$nom);
$sql->bindParam(2,$description);
$sql->execute();


