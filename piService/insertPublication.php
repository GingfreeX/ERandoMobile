<?php

require_once('Config1.php');
define('UPLOAD_DIR', $_SERVER['DOCUMENT_ROOT'] .'/imagesmobile/');
$userId = $_GET['userId'];
$groupId = $_GET['groupId'];
$description = $_GET['description'];
$datepub = date("Y-m-d H:i:s");
$nbJaime = $_GET['nbJaime'];
$imageName = $_POST['image'];
if ($imageName == "\"\"") {
    $sql = "INSERT INTO `publication`(`user_id`, `group_id`, `description`, `datepub`, `photo`, `nbrjaime`) VALUES ('$userId','$groupId','$description','$datepub','\"\"','$nbJaime')";
} else {
    $img = base64_decode($imageName);
    $uid = uniqid();
    $file = $uid . '.png';
    $sql = "INSERT INTO `publication`(`user_id`, `group_id`, `description`, `datepub`, `photo`, `nbrjaime`) VALUES ('$userId','$groupId','$description','$datepub','$file','$nbJaime')";
}

if (mysqli_query($conn, $sql)) {
    file_put_contents($file, $img);
    echo "success";
} else {
    echo "Error: " . $sql;
}
mysqli_close($conn);
?>

