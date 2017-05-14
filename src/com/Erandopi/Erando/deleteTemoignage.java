/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author student1
 */
public class deleteTemoignage {
        Form f ;
         public void delete(Integer id) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/codenameone/pi/delete.php?id=" + id);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                // System.out.println(s);
                if (("success").equals(s)) {
                    Dialog.show("Confirmation", "Temoignage Supprimé", "Ok", null);
                } else {
                    Dialog.show("Erreur", "erreur", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(req);

    }   
}
