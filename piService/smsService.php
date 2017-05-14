<?php
include ( "./smsService/NexmoMessage.php" );
$phone=$_GET['phone'];
// Step 1: Declare new NexmoMessage.
	$nexmo_sms = new NexmoMessage('41555114', '2d3bba06c34f2127');

	// Step 2: Use sendText( $to, $from, $message ) method to send a message. 
	$info = $nexmo_sms->sendText( $phone, 'ERando', ' New Product Added to Store' );

	// Step 3: Display an overview of the message
	echo $nexmo_sms->displayOverview($info);
?>