/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author wassim
 */
public class Membre {
    private int id;
    private String username;
    private String email;
    private String profil_pic;
    private String listamis;
    private String nom;
    private String prenom;
    private int age;
    private String pays;

    public Membre() {
    }

    public Membre(int id) {
        this.id = id;
    }

    public Membre(int id, String username, String email,int age, String nom, String prenom, String profil_pic) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profil_pic = profil_pic;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public Membre(int id, String username, String profil_pic) {
        this.id = id;
        this.username = username;
        this.profil_pic = profil_pic;
    }

    
    public Membre(String username, String email, String profil_pic) {
        this.username = username;
        this.email = email;
        this.profil_pic = profil_pic;
    }

    public Membre(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfil_pic() {
        return profil_pic;
    }

    public void setProfil_pic(String profil_pic) {
        this.profil_pic = profil_pic;
    }

    public String getListamis() {
        return listamis;
    }

    public void setListamis(String listamis) {
        this.listamis = listamis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Membre{" + "id=" + id + ", username=" + username + ", email=" + email + ", profil_pic=" + profil_pic + ", listamis=" + listamis + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", pays=" + pays + '}';
    }
    

    
    

   
  
    
    
    
    
}
