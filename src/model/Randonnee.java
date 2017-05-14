/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author aloulou
 */
public class Randonnee {
    
    
    private int id ;
    private int id_guide ;
    private String titre;
    private String description;
    private String destination;
    private String date ;
    private double prix ;                 
    private String image ;
    private int nbr_places;
    private String point_depart;
    private String type ;
    private int niveau ;
    private int age_min ;
    private String moyen_transport;   

    public Randonnee() {
    }

    public Randonnee(int id, int id_guide, String titre, String description, String destination, String date, double prix, String image, int nbr_places, String point_depart, String type, int niveau, int age_min, String moyen_transport) {
        this.id = id;
        this.id_guide = id_guide;
        this.titre = titre;
        this.description = description;
        this.destination = destination;
        this.date = date;
        this.prix = prix;
        this.image = image;
        this.nbr_places = nbr_places;
        this.point_depart = point_depart;
        this.type = type;
        this.niveau = niveau;
        this.age_min = age_min;
        this.moyen_transport = moyen_transport;
    }
    
    

    public Randonnee(String titre, String description, String destination, String date,double prix, String image, int nbr_places, String point_depart, String type, int niveau, int age_min, String moyen_transport) {
        this.titre = titre;
        this.description = description;
        this.destination = destination;
        this.date = date;
        this.prix = prix;
        this.image = image;
        this.nbr_places = nbr_places;
        this.point_depart = point_depart;
        this.type = type;
        this.niveau = niveau;
        this.age_min = age_min;
        this.moyen_transport = moyen_transport;
    }

    public Randonnee(int id, String titre, String description, String destination, String date, double prix, String image, int nbr_places, String point_depart, String type, int niveau, int age_min, String moyen_transport) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.destination = destination;
        this.date = date;
        this.prix = prix;
        this.image = image;
        this.nbr_places = nbr_places;
        this.point_depart = point_depart;
        this.type = type;
        this.niveau = niveau;
        this.age_min = age_min;
        this.moyen_transport = moyen_transport;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }

    
    
    
    

    
    
    
    
    
    
    
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public String getPoint_depart() {
        return point_depart;
    }

    public void setPoint_depart(String point_depart) {
        this.point_depart = point_depart;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getAge_min() {
        return age_min;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public String getMoyen_transport() {
        return moyen_transport;
    }

    public void setMoyen_transport(String moyen_transport) {
        this.moyen_transport = moyen_transport;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Randonnee other = (Randonnee) obj;
        if (this.id != other.id) {
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "Randonnee{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", destination=" + destination + ", date=" + date + ", prix=" + prix + ", image=" + image + ", nbr_places=" + nbr_places + ", point_depart=" + point_depart + ", type=" + type + ", niveau=" + niveau + ", age_min=" + age_min + ", moyen_transport=" + moyen_transport + '}';
    }
    
    
    
    
    
}



