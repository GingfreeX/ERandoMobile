/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

/**
 *
 * @author student1
 */
public class Temoignage {

    private int id;
    private int iduser_id;
    private String message;

    public Temoignage() {
    }

    public Temoignage(int id, int iduser_id, String message) {
        this.id = id;
        this.iduser_id = iduser_id;
        this.message = message;
    }

    public Temoignage(int iduser_id, String message) {
        this.iduser_id = iduser_id;
        this.message = message;
    }

    
    
     public Temoignage(String message) {
        this.message = message;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        this.id = id;
    }

    public int getIduser_id() {
        return iduser_id;
    }

    public void setIduser_id(int iduser_id) {
        this.iduser_id = iduser_id;
    }

    

   

   

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Temoignage{ message=" + message + '}';
    }


    
}
