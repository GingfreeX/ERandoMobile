<?php
require_once('../Config1.php');

$id=$_GET['id'];

$sql = "select id,titre,description,idMembre,prix,dateAdd,imageName,type from produit where id = ".$id;
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
     // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('produit');
        $mydata->addChild('id',$row['id']);
		$mydata->addChild('titre',$row['titre']);
		$mydata->addChild('description',$row['description']);
		$mydata->addChild('idMembre',$row['idMembre']);
		$mydata->addChild('prix',$row['prix']);
		$mydata->addChild('dateAdd',$row['dateAdd']);
		$mydata->addChild('imageName',$row['imageName']);
		$mydata->addChild('type',$row['type']);
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>