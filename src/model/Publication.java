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
public class Publication {
    private int id ; 
    private String description;
    private  String section;
    private  String datepub ;
    private User user;
    private String imagepath;
    private int idUser;

    public Publication(int id, String description, String section,String datepu, User user) {
        this.id = id;
        this.description = description;
        this.section = section;
         this.datepub =datepu;
        this.user = user;
    }

    public Publication() {
    }

   public Publication(String description, String section, String datepub, User user) {
        this.description = description;
        this.section = section;
        this.datepub = datepub;
        this.user = user;
    }
    public Publication(int id,String description, String section, String datepub, User user,String imagePath) {
         this.id = id;
        this.description = description;
        this.section = section;
        this.datepub = datepub;
        this.user = user;
        this.imagepath=imagePath;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", description=" + description + ", section=" + section + ", datepub=" + datepub + ", user=" + user + '}';
    }

  
    
}
