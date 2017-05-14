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
public class PublicationGr {

    private int id;
    private String description;
    private String date_publication;
    private User createur;
    private Groupe groupe;
    private String photo;
    private int nbrjaime;

    public PublicationGr() {

    }

    public PublicationGr(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public PublicationGr(User createur, Groupe groupe,String description, String photo, int nbrjaime) {
        this.description = description;
        this.createur = createur;
        this.groupe = groupe;
        this.photo = photo;
        this.nbrjaime = nbrjaime;
    }

    public PublicationGr(int id) {
        this.id = id;
    }
    

    public PublicationGr(String description, String date_publication, User createur, Groupe groupe) {
        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
        this.groupe = groupe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public User getCreateur() {
        return createur;
    }

    public void setCreateur(User createur) {
        this.createur = createur;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNbrjaime() {
        return nbrjaime;
    }

    public void setNbrjaime(int nbrjaime) {
        this.nbrjaime = nbrjaime;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", description=" + description + ", date_publication=" + date_publication + ", createur=" + createur + ", groupe=" + groupe + ", photo=" + photo + ", nbrjaime=" + nbrjaime + '}';
    }
    

    

 

   

}
