<?php
require_once('../Config1.php');
$idrando=$_GET['idrando'];
$iduser=$_GET['iduser'];
$sql2 = "select * from randonne where id=".$idrando;
$result = $conn->query($sql2);
 while($row = $result->fetch_assoc()) {
 if($row['nbr_places']==0){
	 echo "complet";
 }else{
	 $sql1 = "INSERT into reservation (id_Randonne,id_user) VALUES('$idrando','$iduser')";


if (mysqli_query($conn, $sql1)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
	$sql = "UPDATE randonne SET nbr_places=nbr_places-1 where id=".$idrando;


if (mysqli_query($conn, $sql)) {
   
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
} 
 }
 
 
 }



mysqli_close($conn);
?>