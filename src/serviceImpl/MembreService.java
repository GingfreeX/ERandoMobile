/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

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
import model.Groupe;

import model.User;

/**
 *
 * @author wassim
 */
public class MembreService {

    private ConnectionRequest con;

    public MembreService() {
    }

    public User getPubMember(int id) {

        User me = new User();
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
                    me.setAge(Integer.parseInt(obj.get("age").toString()));
                    me.setNom(obj.get("nom").toString());
                    me.setPrenom(obj.get("prenom").toString());
                    me.setPays(obj.get("location").toString());
                    me.setListAmis(obj.get("liste_amis").toString());
                    me.setProfil_pic(obj.get("profile_pic").toString());

                } catch (IOException ex) {
                    //Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        con.setUrl("http://localhost/piService/getUserGrById.php?userId=" + id);
        System.out.println(con.getUrl());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (me == null) {
            return null;
        } else {
            return me;
        }
    }

    public Map<Integer, String> getUsernamesWithId(String list_amis) {
        Map<Integer, String> ls = new HashMap<>();
        if (!(list_amis == "" || list_amis == null)) {
            StringTokenizer splitstring = new StringTokenizer(list_amis, "/");
            int[] array = new int[splitstring.countTokens()];
            while (splitstring.hasMoreElements()) {
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(splitstring.nextToken());
                }
            }

            for (int i = 0; i < array.length; i++) {
                ls.put(array[i], getPubMember(array[i]).getUsername());

            }

        }
        return ls;
    }
    
    public List<String> getUsernameOnly(Map<Integer, String> m) {
        List<String> ls = new ArrayList<>();
        for (String i : m.values()) {
            ls.add(i);
        }
        return ls;
    }
    
      public int getUsernameId(Map<Integer, String> m, String username) {
        for (Map.Entry entry : m.entrySet()) {
            if (username.equals(entry.getValue())) {
                return (int) entry.getKey();
            }
        }
        return 0;
    }
}
