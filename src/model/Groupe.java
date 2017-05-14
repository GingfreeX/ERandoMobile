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
public class Groupe {
    private int id;
    private String nom;
    private String description;
    private String photo_couverture;
    private String date_creation;
    private Membre createur;
    private String membres;

      public Groupe() {
        }

    public Groupe(int id) {
        this.id = id;
    }

    public Groupe(int id, String nom, String description, String photo_couverture, String date_creation, Membre createur, String membres) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo_couverture = photo_couverture;
        this.date_creation = date_creation;
        this.createur = createur;
        this.membres = membres;
    }

    public Groupe(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public Groupe(String nom, String description, Membre createur) {
        this.nom = nom;
        this.description = description;
        this.createur = createur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_couverture() {
        return photo_couverture;
    }

    public void setPhoto_couverture(String photo_couverture) {
        this.photo_couverture = photo_couverture;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public Membre getCreateur() {
        return createur;
    }

    public void setCreateur(Membre createur) {
        this.createur = createur;
    }

    public String getMembres() {
        return membres;
    }

    public void setMembres(String membres) {
        this.membres = membres;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", photo_couverture=" + photo_couverture + ", date_creation=" + date_creation + ", createur=" + createur + ", membres=" + membres + '}';
    }

      
   
  
   
    
    
    
    
}
