/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author wassim
 */
public class ImageG {
    private int id;
    private String name;
    private String date_pub;
    private Groupe groupe;
    private Membre membre;

    public ImageG() {
    }

    public ImageG(int id, String name, String date_pub, Groupe groupe, Membre membre) {
        this.id = id;
        this.name = name;
        this.date_pub = date_pub;
        this.groupe = groupe;
        this.membre = membre;
    }

    public ImageG(String name, Groupe groupe, Membre membre) {
        this.name = name;
        this.groupe = groupe;
        this.membre = membre;
    }
    

    
    public ImageG(int id) {
        this.id = id;
    }

    public ImageG(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(String date_pub) {
        this.date_pub = date_pub;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "ImageG{" + "id=" + id + ", name=" + name + ", date_pub=" + date_pub + ", groupe=" + groupe + ", membre=" + membre + '}';
    }
    
    

   
    
    
    
}
