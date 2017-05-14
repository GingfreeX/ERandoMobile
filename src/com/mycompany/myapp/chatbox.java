/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.List;
import model.User;
import model.messages;
import serviceImpl.MessagesServices;
import serviceImpl.UserServices;

/**
 *
 * @author cimope
 */
public class chatbox {

    Form f;
    private static User u;
    EncodedImage encoded;

    public chatbox(Resources theme, User user) {
        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "chatBox");
        Toolbar tl = new Toolbar();
        Container ct = (Container) uib.findByName("Container1", ctn);
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        try {
            encoded = EncodedImage.create("/load.png");
            // Pro only feature, uncomment if you have a pro subscription
            // Log.bindCrashProtection(true);
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        f = ctn.getComponentForm();

        f.getToolbar().addMaterialCommandToLeftBar("retour", FontImage.MATERIAL_ARROW_BACK, e -> {
            ListeAmis l = new ListeAmis(theme);
            l.getF().show();
        });
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                u = new UserServices().getUserByID(new String(con.getResponseData()));
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(5000);
                                ConnectionRequest con2 = new ConnectionRequest();
                                Image imgServer = URLImage.createToStorage(encoded, user.getNom(), "http://localhost/piService/images/" + user.getImagePath());
                                con2.setUrl("http://localhost/piService/selectallmessages.php?author_id=" + u.getId() + "&addressee_id=" + user.getId());
                                con2.addResponseListener(new ActionListener<NetworkEvent>() {

                                    @Override
                                    public void actionPerformed(NetworkEvent evt) {

                                        List<messages> listmes = new MessagesServices().getallmess(new String(con2.getResponseData()));
                                        ct.removeAll();
                                        if (listmes.isEmpty()) {
                                            Label lbl = new Label("envoyer votre premier message à " + user.getNom());
                                            Label l = new Label(" ");
                                            ct.add(l);
                                            ct.revalidate();
                                        } else {
                                            for (messages m : listmes) {

                                                if (m.getIdsender() == u.getId()) {
                                                    Label lbl = new Label(u.getNom() + " " + m.getMessage());
                                                    Label l = new Label(" ");
                                                    ct.add(lbl);
                                                    ct.add(l);
                                                    ct.revalidate();
                                                } else {
                                                    Label lbl = new Label(user.getNom() + " " + m.getMessage());
                                                    Label l = new Label(" ");
                                                    ct.add(lbl);
                                                    ct.add(l);
                                                    ct.revalidate();
                                                }
                                            }
                                        }
                                    }

                                });
                                NetworkManager.getInstance().addToQueue(con2);
                            } catch (InterruptedException ex) {
                                Log.getReportingLevel();
                            }
                            System.out.println("Server is running.....");
                        }
                    }
                });

                t1.start();
            }
        });

        NetworkManager.getInstance().addToQueue(con);

        TextField sandbox = (TextField) uib.findByName("sandbox", ctn);

        Button b1 = (Button) uib.findByName("send", ctn);

        b1.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SEND, b1.getUnselectedStyle()));

        f.getToolbar().addMaterialCommandToRightBar(user.getNom() + "  " + user.getPrenom(), FontImage.MATERIAL_PERSON, e -> {
        });
        b1.addActionListener(x -> {

            ConnectionRequest con1 = new ConnectionRequest();
            con1.setUrl("http://localhost/piService/sendmessage.php?author_id=" + u.getId() + "&addressee_id=" + user.getId() + "&messageText=" + sandbox.getText());
            con1.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {
                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Label l = new Label(" ");
                        Label lbl = new Label(u.getNom() + "   :" + sandbox.getText());
                        ct.add(lbl);
                        ct.add(l);
                        f.refreshTheme();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }

                }
            });
            NetworkManager.getInstance().addToQueue(con1);

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
