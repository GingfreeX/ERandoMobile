<?php

class Config{
private $dbname; 
private $user; 
private $passwd ; 
private  $pdo ;
private  $req ; 

    public function __construct($dbname="e-rando2",$user="root",$passwd=""){

            $this->dbname=$dbname ;
            $this->user=$user ;
            $this->passwd=$passwd;
            
          
    }
    public function getPDO(){
        $pdo = new PDO('mysql:host=localhost;dbname=e-rando2', $this->user, $this->passwd);
        $this->pdo=$pdo ;
        return $pdo ;
    }
    
   

     
}
