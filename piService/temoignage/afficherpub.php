 <?php
require_once('../Config1.php');



$req = "SELECT * FROM publication_e where user_id=17";
$result = $conn->query($req);
$json = new SimpleXMLElement('<xml/>');
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
   $mydata = $json->addChild('publication');
    $mydata->addChild('id', $row['id']);
    $mydata->addChild('user_id', $row['user_id']);
    $mydata->addChild('description', $row['description']);
	$mydata->addChild('image', $row['image']);
    $mydata->addChild('section', $row['section']);
    $mydata->addChild('datepub', $row['datepub']);

     
         }
} else {
    echo "0 results";
}
$conn->close();
	 echo( json_encode ($json));
?>