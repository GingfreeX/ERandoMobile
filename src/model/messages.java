/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author cimope
 */
public class messages {
    private int id ;
    private User sender;
    private User reciver;
    private int  idsender;
    private int idreciver;
    private String message;

    public messages(int id, User sender, User reciver, String message) {
        this.id = id;
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
    }

    
    public  messages(){
        
    }
    public messages( User sender, User reciver, String message) {
    
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciver() {
        return reciver;
    }

    public void setReciver(User reciver) {
        this.reciver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int idsender) {
        this.idsender = idsender;
    }

    public int getIdreciver() {
        return idreciver;
    }

    public void setIdreciver(int idreciver) {
        this.idreciver = idreciver;
    }

}