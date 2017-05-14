/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;



/**
 *
 * @author cimope
 */
public class PublicationT {
    private int id ; 
    private String description;
    private  String section;
    private  String datepub ;

    private String imagepath;
    private int idUser;

    public PublicationT(int id, String description, String section,String datepu) {
        this.id = id;
        this.description = description;
        this.section = section;
         this.datepub =datepu;

    }

    public PublicationT() {
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDatepub() {
        return datepub;
    }

    public void setDatepub(String datepub) {
        this.datepub = datepub;
    }


    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        if(imagepath==null){
            this.imagepath=null;
        }
        this.imagepath = imagepath;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    
  
    
}
