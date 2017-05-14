<?php
require_once('class.phpmailer.php');
$email=$_GET['email'];
//include("class.smtp.php"); // optional, gets called from within class.phpmailer.php if not already loaded

$mail = new PHPMailer(true); // the true param means it will throw exceptions on errors, which we need to catch

$mail->IsSMTP(); // telling the class to use SMTP

try {
  $mail->Host       = "mail.yourdomain.com"; // SMTP server
  $mail->SMTPDebug  = 2;                     // enables SMTP debug information (for testing)
  $mail->SMTPAuth   = true;                  // enable SMTP authentication
  $mail->SMTPSecure = "ssl";                 // sets the prefix to the servier
  $mail->Host       = "smtp.gmail.com";      // sets GMAIL as the SMTP server
  $mail->Port       = 465;                   // set the SMTP port for the GMAIL server
  $mail->Username   = "farhani.hmida@gmail.com";  // GMAIL username
  $mail->Password   = "/Farhani/";            // GMAIL password
  $mail->AddReplyTo('farhani.hmida@gmail.com', 'Host&Guest');
  $mail->AddAddress($email);
  $mail->SetFrom('farhani.hmida@gmail.com', 'ERANDO');
  $mail->AddReplyTo('farhani.hmida@gmail.com', 'ERANDO');
  $mail->Subject = 'Product Added';
  $mail->AltBody = 'To view the message, please use an HTML compatible email viewer!'; // optional - MsgHTML will create an alternate automatically
  $mail->MsgHTML('Product Added to Store , Go and have a look !');
  
  $mail->Send();
  echo "Message Sent OK</p>\n";
} catch (phpmailerException $e) {
  echo $e->errorMessage(); //Pretty error messages from PHPMailer
} catch (Exception $e) {
  echo $e->getMessage(); //Boring error messages from anything else!
}
?>
