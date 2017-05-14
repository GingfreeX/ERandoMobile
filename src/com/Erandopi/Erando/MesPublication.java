/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
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
 * @author student1
 */
public class MesPublication {
    Form f ; 
    EncodedImage encoded;
    public  MesPublication(Resources theme){
        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme,"mespublication");
        f = ctn.getComponentForm();

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/codenameone/pi/afficherpub.php" );
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    encoded = EncodedImage.create("/loader.png");
                    // Pro only feature, uncomment if you have a pro subscription
                    // Log.bindCrashProtection(true);
                } catch (IOException ex) {
                    //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
                }


                List<Publication> listpub = getListPublication(new String(con.getResponseData()));
                for(Publication p :listpub){
                     Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                     Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label lbl1 = new Label(p.getSection());
                    Label lbl2 = new Label(p.getDescription());

                    ctn2.add(lbl1);
                    ctn2.add(lbl2);
                     Image imgServer = URLImage.createToStorage(encoded,p.getDescription(), "http://localhost/codenameone/pi/images/"+p.getImagepath() );

                    ctn1.add(ctn2);
                    lbl1.addPointerPressedListener(x->{
                    detailspublication l = new detailspublication(theme, p);
                    l.getF().show();
                    
                    });
                    ctn1.setLeadComponent(lbl1);
                    ctn3.add(ctn1);
                    ctn3.add(new ImageViewer(imgServer));
                    System.out.println( "http://localhost/codenameone/pi/images/"+p.getImagepath() );
                    f.add(ctn3);
                    
                }

          

  
        f.refreshTheme();

        f.getToolbar().addCommandToOverflowMenu("retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
    ajouterpublication m = new ajouterpublication(theme);
    m.getF().show();
            }

                   
        });

            }
        });
        NetworkManager.getInstance().addToQueue(con);

        

    
    }
public ArrayList<Publication> getListPublication(String json) {
        ArrayList<Publication> listPublication = new ArrayList<Publication>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> publications = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("publication");

            for (Map<String, Object> obj : list) {
                Publication p = new Publication();
                p.setId(Integer.parseInt(obj.get("id").toString()));
                p.setSection(obj.get("section").toString());
                p.setDatepub(obj.get("datepub").toString());
                p.setDescription(obj.get("description").toString());
                p.setIdUser(Integer.parseInt(obj.get("user_id").toString()));
                p.setImagepath(obj.get("image").toString());

   
                listPublication.add(p);

            }

        } catch (IOException ex) {
         }
        return listPublication;

    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
