/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.sun.webkit.ThemeClient;
import java.util.ArrayList;
import model.Groupe;
import model.User;
import serviceImpl.GroupeService;

/**
 *
 * @author wassim
 */
public class GroupFrom {
    Resources themegr;

    public void MesGroupes(Resources theme, Form fprevious) {
        int iduser = User.getIdofuserAlreadyloggedin();
        Form f = new Form("Mes groupes");
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            fprevious.showBack();
        });
        f.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, ev -> {
            Dialog d = new Dialog("Creer un groupe", new BorderLayout());
            TextField txtgrp = new TextField();
            txtgrp.setHint("Nom du groupe");
            TextField txtdes = new TextField();
            txtdes.setHint("Description du groupe");
            d.add(BorderLayout.NORTH, txtgrp);
            d.add(BorderLayout.CENTER, txtdes);
            Button btn = new Button("Creer");
            btn.addActionListener(e->{
                Groupe g = new Groupe();
                g.setNom(txtgrp.getText());
                g.setDescription(txtdes.getText());
                g.setCreateur(new User(iduser));
                new GroupeService().add(g);
                Dialog.show("Succés","Groupe crée", "ok",null);
                fprevious.showBack();
            });
            d.add(BorderLayout.SOUTH, btn);
            d.showStretched(BorderLayout.CENTER, true);
        });
        ArrayList<Groupe> lsgr = new GroupeService().getGroupe();
        if (lsgr.isEmpty()) {
            System.out.println("vide");
        }
        else{
        for (Groupe g : lsgr) {
        Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ct = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer img = new ImageViewer(theme.getImage("group.png"));
        Label groupenom = new Label(g.getDescription());
        c.add(img);
        ct.add(groupenom);
        c.add(ct);
        groupenom.addPointerReleasedListener(ev -> {

                    //display detail
                    Groupe.setIdgrpconnected(g.getId());
                    
                            themegr = UIManager.initFirstTheme("/theme");

                    new PublicationForm().displayPublication(themegr);

                });

                ct.setLeadComponent(groupenom);
                
        f.add(c);
            
        }
        }
        f.show();

    }

}
