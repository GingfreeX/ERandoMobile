/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import model.Randonnee;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author aloulou
 */
public class listrando {
      private static  final String apiKey="AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI";

    Form f ;
    EncodedImage placeholder;
 //int Usr_id=2;
     int Usr_id=1;
     
     
    public listrando(Resources theme ){
         UIBuilder ui = new UIBuilder();
        f= ui.createContainer(theme, "GUI 1").getComponentForm();
        f.setUIID("addRando");
        f.refreshTheme();
        
        f.getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->{
  addRando add = new addRando(theme);
  add.getF().show();
  
  });
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codenameone/select.php");
       
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("aywah"+getListRandonnee(new String(con.getResponseData())));
                ArrayList<Randonnee> lst = getListRandonnee(new String(con.getResponseData()));
 for(Randonnee ls :lst){
                    UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Label lbl = new Label(ls.getTitre());
                        Label lbl1 = new Label(ls.getDestination());
                        Label lbl2 = new Label(ls.getPoint_depart());
                        Label lbl3 = new Label(ls.getDate()+"");                    
                        Label lbl4 = new Label(ls.getPrix()+"");
                        Label lbl5=new Label() ;
                        if(ls.getNbr_places()<=0){
                       lbl5.setText(""+ls.getNbr_places());
                        
                        }
                        else{System.out.println(ls.getId());
                        
                       if(Usr_id==1){
                        lbl1.addPointerPressedListener(x->{
                        
                            
                            
                            
                            
                        UpdateRando e = new UpdateRando(theme);
                        
                        e.UpdateRando(ls,theme);
                        });}
                       else {
                           f.getToolbar().removeAll();
                           
                           lbl1.addPointerReleasedListener(x->{
                            Form f5 =new Form("reserver",BoxLayout.y());
                            f5.setUIID("addRando");
                            
                        Container ctn4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                        Container ctn5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container ctn6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        //Label lbl10 = new Label("Titre :"+ls.getTitre());
                        Label lbl11 = new Label("Destination :"+ls.getDestination());
                        Label lbl12 = new Label("Point de départ :"+ls.getPoint_depart());
                        Label lbl13 = new Label("Date de Sortie :"+ls.getDate()+"");                    
                        Label lbl14 = new Label("prix :"+ls.getPrix()+"");
                        Label lbl16 = new Label("Moyen de transport :"+ls.getMoyen_transport());
                        Label lbl17 = new Label("Age min :"+ls.getAge_min());
                        Label lbl18 = new Label("Niveau de difficulté :"+ls.getNiveau());
                        Label lbl19 = new Label("Type :"+ls.getType());

                        
                        Label lbl15=new Label() ;
                        if(ls.getNbr_places()<=0){
                       lbl5.setText(""+ls.getNbr_places());
                        
                        }
                        else{System.out.println(ls.getId());
                        
                       
                        }
                        
                        
                        //ctn5.add(lbl10);
                        ctn5.add(lbl11);
                        ctn5.add(lbl12);
                        ctn5.add(lbl13);
                        ctn5.add(lbl14);
                        ctn5.add(lbl15);
                        ctn5.add(lbl16);
                        ctn5.add(lbl17);
                        ctn5.add(lbl18);
                        ctn5.add(lbl19);
                        ctn5.getStyle().setPaddingBottom(10);
                          
                        
                        //MAP
                       //--------------------------------------------------------- --------------------------------
                        MapContainer map = new MapContainer(apiKey);
                           
                          // map.calculateAndDisplayAnnonceRoute("Grombalia, Nabeul","ESPRIT, Ariana");
                         map.calculateAndDisplayAnnonceRoute(lbl12.getText(),lbl11.getText());

                         map.setmapSize(800,100);
                           
                     //  ctn5.add(map);
                            
                            
                           Button b1= new  Button("reserver");
                           ctn5.add(b1);
                           f5.add(ctn5).add(map);
                           
                      //--------------------------------------------------------- --------------------------------

                         // f5.add(ctn5);
                           f5.show();
                           b1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
ConnectionRequest con14 = new ConnectionRequest();
con14.setUrl("http://localhost/codenameone/ajouter_reservation.php?idrando="+ls.getId()+"&iduser="+Usr_id);
con14.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                          byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                      
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                         
                        }else if(s.equals("complet")){
            Dialog.show("complet", "complet", "Ok", null);

                        }else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    
                    }
           
         });
NetworkManager.getInstance().addToQueue(con14);
   
                        }
                            });
                           
                                    f5.getToolbar().addCommandToOverflowMenu("Back", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
        f.show();

  //l.getF().showBack();
         }
     });
                           });
                      
                           
                      lbl5.setText("nombre de :"+ls.getNbr_places());
                       
                       }}
                        
                        
                       
                        
                        
                        ctn2.add(lbl);
                        ctn2.add(lbl1);
                        ctn2.add(lbl2);
                        ctn2.add(lbl3);
                        ctn2.add(lbl4);
                        ctn2.add(lbl5);
                        ctn2.getStyle().setPaddingBottom(20);

                             ConnectionRequest con = new ConnectionRequest();

                    try {
                        placeholder = EncodedImage.create("/spinner.png");
                    } catch (IOException ex) {
//                        Logger.getLogger(listrando.class.getName()).log(Level.SEVERE, null, ex);
                    }
 
   Image i = URLImage.createToStorage(placeholder,ls.getImage(), "http://localhost/codenameone/images/"+ls.getImage()); 
     System.out.println("http://localhost/codenameone/images/"+ls.getImage()); 
     Label l = new Label();
     l.setIcon(i);
   ctn1.add( l);

                        ctn1.add(ctn2);
                        ctn1.setLeadComponent(lbl1);
                        ctn3.add(ctn1);
                    


                        f.add(ctn3);
              }

            }
        });
        NetworkManager.getInstance().addToQueue(con);
        
     
    
        
    }

   /* listrando() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
      public ArrayList<Randonnee> getListRandonnee(String json) {
        ArrayList<Randonnee> listRandonnees = new ArrayList<>();
        ArrayList<Randonnee> aywah = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> randonnees = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) randonnees.get("randonne");
            System.out.println("hello  "+list);
            for (Map<String, Object> obj : list) {
                Randonnee r = new Randonnee();
                
                
               
               // r.setTitre(obj.get("titre").toString());
                //r.setDescription(obj.get("description").toString());    
                 r.setId(Integer.parseInt(obj.get("id").toString()));
                r.setDestination(obj.get("destination").toString());
                r.setPrix(Double.parseDouble(obj.get("prix").toString()));       
                r.setImage(obj.get("image").toString());                             
                r.setNbr_places(Integer.parseInt(obj.get("nbr_places").toString()));              
                r.setPoint_depart(obj.get("point_depart").toString());
                r.setType(obj.get("type").toString());           
                r.setNiveau(Integer.parseInt(obj.get("niveau").toString()));
                r.setAge_min(Integer.parseInt(obj.get("age_min").toString()));
                r.setMoyen_transport(obj.get("moyen_transport").toString());
                r.setDate(obj.get("date").toString());

                 
                
                System.out.println("tttt"+r);
                aywah.add(r);
 
            }

        // hi.show();
        }catch (IOException ex) {
         }
        return aywah;
            
           //  System.out.println("aywah"+listRandonnees);

           
          // java.util.List<Map<String, Object>> data = getListRandonnee();
                       // System.out.println("data "+data.size());

           
           
           
           
                
           
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    
    
}
