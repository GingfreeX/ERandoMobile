<?php
require_once('../Config1.php');

$type=$_GET['type'];

$sql = "select emailMembre from subscribers where type='$type'";
$result = $conn->query($sql);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
      $mydata = $json->addChild('subscriber');
        $mydata->addChild('emailMembre',$row['emailMembre']);

         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>