/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import serviceImpl.UserServices;

/**
 *
 * @author cimope
 */
public class ListeAmis {

    Form f;
    EncodedImage encoded;

    public ListeAmis(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "ListeAmis");
        f = ctn.getComponentForm();
        try {
            encoded = EncodedImage.create("/load.png");
            // Pro only feature, uncomment if you have a pro subscription
            // Log.bindCrashProtection(true);
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
  f.getToolbar().addMaterialCommandToLeftBar("retour", FontImage.MATERIAL_ARROW_BACK, e -> {
           Home p = new Home(theme);
                p.getF().showBack();
        });
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/getUserByEmail.php?email="+User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                String listamis = new UserServices().getUserByID(new String(con.getResponseData())).getListAmis();
                List<String> l = StringUtil.tokenize(listamis, "/");

                for (String i : l) {
                    ConnectionRequest con2 = new ConnectionRequest();

                    con2.setUrl("http://localhost/piService/getUserByID.php?id=" + Integer.parseInt(i));
                    con2.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                            User u = new UserServices().getUserByIDsecond(new String(con2.getResponseData()));
                            Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Label lbl1 = new Label(u.getNom() + " " + u.getPrenom());
                            Image imgServer = URLImage.createToStorage(encoded, u.getNom(), "http://localhost/piService/images/" + u.getImagePath());

                            Label image = new Label();
                            image.setIcon(imgServer);
                            ctn1.add(image);
                            ctn1.add(lbl1);
                            ctn2.add(ctn1);
                            ctn1.setLeadComponent(image);
                            image.addPointerPressedListener(x -> {

                                chatbox ct = new chatbox(theme, u);
                                ct.getF().show();

                            });

                            f.add(ctn2);

                        }
                    });

                    NetworkManager.getInstance().addToQueue(con2);

                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
