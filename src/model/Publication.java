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
public class Publication {

    private int id;
    private String description;
    private String date_publication;
    private Membre createur;
    private Groupe groupe;
    private String photo;
    private int nbrjaime;

    public Publication() {

    }

    public Publication(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Publication(Membre createur, Groupe groupe,String description, String photo, int nbrjaime) {
        this.description = description;
        this.createur = createur;
        this.groupe = groupe;
        this.photo = photo;
        this.nbrjaime = nbrjaime;
    }

    public Publication(int id) {
        this.id = id;
    }
    

    public Publication(String description, String date_publication, Membre createur, Groupe groupe) {
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

    public Membre getCreateur() {
        return createur;
    }

    public void setCreateur(Membre createur) {
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
