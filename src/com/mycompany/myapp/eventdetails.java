/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import model.Randonnee;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author aloulou
 */
public class eventdetails {
    Form f ; 
  
  public eventdetails(Resources theme,Randonnee r){
      
      
         UIBuilder ui = new UIBuilder();
        f= ui.createContainer(theme, "Details").getComponentForm();
        f.setUIID("addRando");
        
     f.getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->{
  listrando l = new listrando(theme);
  l.getF().show();
  
  });
     
      Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
      Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
  Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     
      Label lb2 = new Label("prix : "+r.getPrix()+"");
       Label lb4 = new Label("Nombre de place : "+r.getNbr_places()+"");
        Label lb1 = new Label("lieu depart : "+r.getPoint_depart());
      Label lb3 = new Label("lieu arrivée : "+r.getDestination());
      Button b1 = new Button("modfier");
      b1.addActionListener(l->{
      UpdateRando u = new UpdateRando(theme);
      
      });
         ctn2.add(lb1);
        ctn2.add(lb2);
        ctn2.add(lb3);
        ctn2.add(b1);
      ctn3.add(ctn2);
      f.add(ctn3);
  
              
      
  }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
  
  
}
