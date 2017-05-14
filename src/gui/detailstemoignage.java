/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import model.Temoignage;

/**
 *
 * @author student1
 */
public class detailstemoignage {

    Form f;    

    public detailstemoignage(Resources theme, Temoignage t) {
        UIBuilder uib = new UIBuilder();
        
        Container ctn1 = uib.createContainer(theme, "GUI 3");
        f = ctn1.getComponentForm();
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        TextField txt = new TextField(t.getMessage());
        
        txt.setEditable(false);
        txt.setUIID("container");
        ctn2.add(txt);
        
        Button btnsupprimer = new Button("supprimer");
        btnsupprimer.addActionListener(e -> {
            new deleteTemoignage().delete(t.getId());
        });
        Button btnmodif = new Button("Modifier");
        btnmodif.addActionListener(e -> {
            txt.setEditable(true);
            txt.setUIID("TextField");
            txt.addActionListener(ev -> {
                t.setMessage(txt.getText());
                new updateTemoignage().update(theme, t);
                
            });
            
        });
        ctn2.add(btnsupprimer);
        ctn2.add(btnmodif);
        f.add(ctn2);
                f.refreshTheme();
f.getToolbar().addCommandToOverflowMenu("retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
    affichertemoignange m = new  affichertemoignange(theme);
    m.getF().show();
            }

                   
        });
    }
    
    public Form getF() {
        return f;
    }
    
    public void setF(Form f) {
        this.f = f;
    }
    
}
