/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Erandopi.Erando;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 *
 * @author wassim
 */
public class MembreService {

    private ConnectionRequest con;

    public MembreService() {
    }

    public Membre getPubMember(int id) {

        Membre me = new Membre();
        con = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    Map<String, Object> obj = (Map<String, Object>) data.get("users");
                    me.setId(Integer.parseInt(obj.get("id").toString()));
                    me.setUsername(obj.get("username").toString());
                    me.setEmail(obj.get("email").toString());
                } catch (IOException ex) {
                    //Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        con.setUrl("http://localhost/piService/getUserById.php?userId=" + id);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (me == null) {
            return null;
        } else {
            return me;
        }
    }
}
