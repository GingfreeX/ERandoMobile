<?php
require_once('../Config1.php');



$sql = "SELECT * FROM temoignage";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {

      $mydata = $json->addChild('temoignage');
       $mydata->addChild('id',$row['id']);
        $mydata->addChild('iduser_id',$row['iduser_id']);
      $mydata->addChild('message',$row['message']);

     
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>