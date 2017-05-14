<?php  
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "e-rando2";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if (!mysqli_set_charset($conn, "utf8")) {
    printf("Erreur lors du chargement du jeu de caractères utf8 : %s\n", mysqli_error($conn));
    exit();
}