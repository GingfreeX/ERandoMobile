/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author student1
 */
public class updateTemoignage {
      Form f; 

      public void update(Resources theme,Temoignage t) {
        UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "GUI 4");
        f = ctn1.getComponentForm();
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/codenameone/pi/update.php?message=" + t.getMessage() + "&id=" +t.getId());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                // System.out.println(s);
                if (("success").equals(s)) {
                    Dialog.show("Confirmation", "temoignage Modifié", "Ok", null);
                } else {
                    Dialog.show("Erreur", "erreur", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);
        
      }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
