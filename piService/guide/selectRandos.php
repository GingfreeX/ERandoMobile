<?php
require_once('../Config1.php');



$sql = "SELECT * FROM randonnee	where id_guide=1";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('randonne');
	  
	  
			$mydata->addChild('id',$row['id']);
	  		$mydata->addChild('prix',$row['prix']);
//$mydata->addChild('titre',$row['titre']);
       // $mydata->addChild('titre',$row['titre']);
        //$mydata->addChild('description',$row['description']);
        $mydata->addChild('destination',$row['destination']);
        $mydata->addChild('image',$row['image']);
        $mydata->addChild('nbr_places',$row['nbr_places']);	
		$mydata->addChild('point_depart',$row['point_depart']);
        $mydata->addChild('type',$row['type']);
        $mydata->addChild('niveau',$row['niveau']);	
		$mydata->addChild('age_min',$row['age_min']);
		$mydata->addChild('moyen_transport',$row['moyen_transport']);


        
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>
