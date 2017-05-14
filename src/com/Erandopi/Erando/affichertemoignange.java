/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

import com.codename1.components.SpanLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author student1
 */
public class affichertemoignange {
    Form hi , f3; 
    public affichertemoignange(Resources theme ){
         
        UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "GUI 1");
        Container ctn2 = uib.createContainer(theme, "GUI 5");
        
        
       
        hi=ctn1.getComponentForm();
        f3=ctn2.getComponentForm();
       
        Button btnok = new Button("Ajouter");

   
        hi.add(btnok);
        Button rate = new Button("rate");
        rate.addActionListener(e->{
            showStarPickingForm();
        });
        hi.add(rate);

        
    hi.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
         MyApplication m = new MyApplication();
         }
     });
    
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codenameone/pi/select.php");
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
//                System.out.println(getListTemoignage(new String(con.getResponseData())));
//                sp.setText(getListTemoignage(new String(con.getResponseData())) + "");
//                hi.refreshTheme();
for (Temoignage T : getListTemoignage(new String(con.getResponseData()))){
    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label l = new Label(T.getMessage());
    ctn2.add(l);
    l.addPointerPressedListener(x->{
    detailstemoignage t = new detailstemoignage(theme, T);
    t.getF().show();
    
    
    });
    
    
    System.out.println(T.getMessage());
  
    hi.add(ctn2);    
    
}
               

            }
          
        });
        
        NetworkManager.getInstance().addToQueue(con);

        btnok.addActionListener(e -> {
           ajouterTemoiganage aj = new ajouterTemoiganage(theme);
           aj.getF().show();
        });

       
    }
    
    public ArrayList<Temoignage> getListTemoignage(String json) {
        ArrayList<Temoignage> listTemoignages = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> temoignages = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) temoignages.get("temoignage");

            for (Map<String, Object> obj : list) {
                Temoignage e = new Temoignage();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setIduser_id(Integer.parseInt(obj.get("iduser_id").toString()));
                e.setMessage(obj.get("message").toString());
                listTemoignages.add(e);

            }

        } catch (IOException ex) {
        }
        return listTemoignages;

    }


    public Form getF() {
        return hi;
    }

    public void setF(Form f) {
        this.hi= f;
    }
    
  private void initStarRankStyle(Style s, Image star) {
 s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
 s.setBorder(Border.createEmpty());
 s.setBgImage(star);
 s.setBgTransparency(0);
}
private Slider createStarRankSlider() {
 Slider starRank = new Slider();
 starRank.setEditable(true);
 starRank.setMinValue(0);
 starRank.setMaxValue(10);
 Font fnt =
 Font.createTrueTypeFont("native:MainLight", "native:MainLight").
 derive(Display.getInstance().convertToPixels(5, true),
 Font.STYLE_PLAIN);
 Style s = new Style(0xffff33, 0, fnt, (byte)0);
 Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR,
 s).toImage();

 s.setOpacity(100);
 s.setFgColor(0);
 Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR,
 s).toImage();
 initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
 initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(),
 emptyStar);
 initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
 initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
 starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5,
 fullStar.getHeight()));

 starRank.addActionListener(
        e->{Dialog.show("Evaluation ", "Merci d'evaluer notre rando", "OK", "Annuler");
        });
  return starRank;
}
private void showStarPickingForm() {
 Form f3 = new Form("Merci d'evaluer notre rando :) ", new BoxLayout(BoxLayout.Y_AXIS));
 f3.add(FlowLayout.encloseCenter(createStarRankSlider()));

    
 f3.show();

 

}  

}
