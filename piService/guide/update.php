<?php
require_once('../Config1.php');
//$image=$_GET['image'];
//$titre=$_GET['titre'];
//$description=$_GET['description'];
//$date=$_GET['date'];

$date=$_GET['date'];
$destination=$_GET['destination'];
$prix=$_GET['prix'];
$nbr_places=$_GET['nbr_places'];
$point_depart=$_GET['point_depart'];
$type=$_GET['type'];
$niveau=$_GET['niveau'];
$age_min=$_GET['age_min'];
$moyen_transport=$_GET['transport'];
$id=$_GET['id'];

$sql= "UPDATE randonne SET destination='".$destination."',prix=".$prix.",date='".$date."',nbr_places=".$nbr_places.",point_depart='".$point_depart."',type='".$type."',niveau='".$niveau."',age_min=".$age_min.",moyen_transport='".$moyen_transport."' where id=".$id." ";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>