package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import gui.GroupFrom;
import gui.ProduitApplication;
import java.io.IOException;
import model.Publication;
import model.User;
import serviceImpl.PublicationServices;
import serviceImpl.UserServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author cimope
 */
public class Home {

    Form f;
    EncodedImage encoded;

    public Home(Resources theme) {

        UIBuilder uib = new UIBuilder();
        Container ctn = uib.createContainer(theme, "MainMenu");
        f = ctn.getComponentForm();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/getUserByEmail.php?email=" + User.getMyemail());
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                try {
                    encoded = EncodedImage.create("/load.png");
                    // Pro only feature, uncomment if you have a pro subscription
                    // Log.bindCrashProtection(true);
                } catch (IOException ex) {
                    //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                Image imgServer = URLImage.createToStorage(encoded, new UserServices().getUserByID(new String(con.getResponseData())).getEmail(), "http://localhost/piService/images/" + new UserServices().getUserByID(new String(con.getResponseData())).getImagePath());
                Image profilePic = imgServer;
                Image mask = theme.getImage("profile.png");
                mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
                profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
                Label profilePicLabel = new Label(profilePic);
                profilePicLabel.setIcon(mask);

                Container sidemenuTop = BorderLayout.center(profilePicLabel);
                sidemenuTop.setUIID("SidemenuTop");

                f.getToolbar().addComponentToSideMenu(sidemenuTop);
                f.getToolbar().addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_DASHBOARD, e -> {
            Home h = new Home(theme);
            h.getF().show();
            
        });
           
        f.getToolbar().addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_FOLDER, e -> {
            profile p = new profile(theme);
            p.getF().show();
        });
        f.getToolbar().addMaterialCommandToSideMenu("Mes AMIS", FontImage.MATERIAL_PEOPLE, e -> {
            ListeAmis l = new ListeAmis(theme);
            l.getF().show();
        });
        
        f.getToolbar().addMaterialCommandToSideMenu("Mes Groupes", FontImage.MATERIAL_GROUP, e -> {
           new GroupFrom().MesGroupes(theme, f);
        });
        
        f.getToolbar().addMaterialCommandToSideMenu("Boutique", FontImage.MATERIAL_STORE, e -> {
                   
                       new ProduitApplication().start(theme);

        });

        f.getToolbar().addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {

            Login l = new Login(theme);
            l.getF().show();

        });
        f.refreshTheme();

        f.getToolbar().addCommandToOverflowMenu("Se deconnecter", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });

            }
        });
        NetworkManager.getInstance().addToQueue(con);

        

    }

    public void getAllNews(Form F, Resources theme) {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/getAllNews.php");

        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                for (Publication p : new PublicationServices().getListPublication(new String(con.getResponseData()))) {
                    UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);

                    Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Label lbl1 = new Label(p.getSection());
                    Label lbl2 = new Label(p.getDescription());

                    ctn2.add(lbl1);
                    ctn2.add(lbl2);

                    ConnectionRequest con = new ConnectionRequest();

                    con.setUrl("http://localhost/piService/getUserByID.php?id=" + p.getIdUser());
                    con.addResponseListener(new ActionListener<NetworkEvent>() {

                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            new UserServices().getUserByID(new String(con.getResponseData())).toString();
                            p.setUser(new UserServices().getUserByID(new String(con.getResponseData())));;
                            System.out.println(p.getUser().getNom());
                            Label lbl3 = new Label(p.getUser().getNom());
                            ctn2.add(lbl3);
                        }
                    });
                    NetworkManager.getInstance().addToQueue(con);
                    ctn1.add(new ImageViewer(theme.getImage("logo.png")));

                    ctn1.add(ctn2);
                    ctn3.add(ctn1);
                    ctn3.add(new ImageViewer(theme.getImage("hiking.jpg")));

                    F.add(ctn3);

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
