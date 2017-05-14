/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author student1
 */
public class ajouterTemoiganage {

    Form f;

    public ajouterTemoiganage(Resources theme) {
        insertTemoignage(theme);
    }

    public void insertTemoignage(Resources theme) {
        UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "GUI 2");
        f = ctn1.getComponentForm();

        TextField tfMessage = new TextField("", "MESSAGE");

        f.add(tfMessage);

        Button btnOk = new Button("Insert");

        f.add(btnOk);
        Button mail = new Button("envoyer mail");

        f.add(mail);
mail.addActionListener(new ActionListener() {
              
          
    
            @Override
            public void actionPerformed(ActionEvent evt) {
               Message m = new Message("Body of message");

Display.getInstance().sendMessage(new String[] {"khaoula.jemai@esprit.tn"}, "Subject of message", m);
            }
        });


f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
        affichertemoignange retour =  new affichertemoignange(theme);
        retour.getF().showBack();
         }
     });
        btnOk.addActionListener(new ActionListener() {
                   

            @Override
            public void actionPerformed(ActionEvent evt) {
         
                ConnectionRequest req = new ConnectionRequest();
                int id =17;
                req.setUrl("http://localhost/piService/temoignage/insert.php?message=" + tfMessage.getText() + "&iduser="+id);

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);
                                     } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });
                
               

                NetworkManager.getInstance().addToQueue(req);
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
