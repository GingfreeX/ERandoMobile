/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import model.Groupe;
import model.ImageG;
import model.User;
import model.PublicationGr;

/**
 *
 * @author wassim
 */
public class GroupeService {

    private ConnectionRequest con;
    Groupe g = new Groupe(2);
    private boolean existe;
    public int nbmember = 0;
    public int nbmemberexiste = 0;
        public int nbimg = 0;
        public int nbgr =0;


    MembreService ms = new MembreService();

    public void add(Groupe groupe) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/createGroupe.php?nom="+groupe.getNom()+"&des="+groupe.getDescription()+"&userId="+groupe.getCreateur().getId());
        System.out.println(con.getUrl());
        NetworkManager.getInstance().addToQueue(con);

    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public void update(Groupe t) {
    }

    public void delete(Integer id) {
    }

    public ArrayList<Groupe> getGroupe() {

        ArrayList<Groupe> lsgr = new ArrayList<>();

        con = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    nbgr = groupeNumber(data);
                    if (nbgr == 0) {
                        ArrayList<Groupe> lsgr = new ArrayList<>();

                    } 
                    if (nbgr == 1) {
                        Map<String, Object> obj = (Map<String, Object>) data.get("groupe");
                            Groupe p = new Groupe();
                        p.setId(Integer.parseInt(obj.get("id").toString()));
                        p.setDescription(obj.get("description").toString());
                        p.setCreateur(new User(Integer.parseInt(obj.get("id_createur").toString())));
                        lsgr.add(p);

                    } 
                    if (nbgr >1){
                        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("groupe");

                        for (Map<String, Object> obj : list) {
                            Groupe p = new Groupe();
                            
                        p.setId(Integer.parseInt(obj.get("id").toString()));
                        p.setDescription(obj.get("description").toString());
                        p.setCreateur(new User(Integer.parseInt(obj.get("id_createur").toString())));
                            lsgr.add(p);
                        }
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/getGroupByCreator.php?userId=" + User.getIdofuserAlreadyloggedin());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (lsgr == null) {
            return null;
        } else {

            return lsgr;
        }

    
    }

    public Groupe getGroupebyId(int id) {
        Groupe g1 = new Groupe();
        con = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();

                Reader reader = new InputStreamReader(in, "UTF-8");
                Map<String, Object> data = json.parseJSON(reader);
                Map<String, Object> obj = (Map<String, Object>) data.get("groupe");
                g1.setId(Integer.parseInt(obj.get("id").toString()));
                g1.setNom(obj.get("nom").toString());
                g1.setDescription(obj.get("description").toString());
                g1.setPhoto_couverture(obj.get("photoCouverture").toString());
                g1.setCreateur(new User(Integer.parseInt(obj.get("id_createur").toString())));
                g1.setDate_creation(obj.get("date_creation").toString());

            }

        };
        con.setUrl("http://localhost/piService/getGroupeById.php?groupId=" + id);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (g1 == null) {
            return null;
        } else {
            return g1;
        }

    }

    public ArrayList<User> getAllm() {
        ArrayList<User> am = new ArrayList<>();

        con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    nbmember = memberNumber(data);
                    if (nbmember == 0) {
                        ArrayList<User> am = new ArrayList<>();

                    }
                    if (nbmember == 1) {
                        Map<String, Object> obj = (Map<String, Object>) data.get("users");
                        User me = new User();
                        me.setId(Integer.parseInt(obj.get("id").toString()));
                        me.setUsername(obj.get("username").toString());
                        me.setEmail(obj.get("email").toString());
                        me.setAge(Integer.parseInt(obj.get("age").toString()));
                        me.setNom(obj.get("nom").toString());
                        me.setPrenom(obj.get("prenom").toString());
                        me.setPays(obj.get("location").toString());
                        me.setProfil_pic(obj.get("profile_pic").toString());
                        am.add(me);
                    }

                    if (nbmember > 1) {
                        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("users");
                        for (Map<String, Object> obj : list) {
                            User me = new User();
                            me.setId(Integer.parseInt(obj.get("id").toString()));
                            me.setUsername(obj.get("username").toString());
                            me.setEmail(obj.get("email").toString());
                            me.setAge(Integer.parseInt(obj.get("age").toString()));
                            me.setNom(obj.get("nom").toString());
                            me.setPrenom(obj.get("prenom").toString());
                            me.setPays(obj.get("location").toString());
                            me.setProfil_pic(obj.get("profile_pic").toString());
                            am.add(me);
                        }
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/getGroupMember.php?groupId=" + g.getId());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (am == null) {
            return null;
        } else {
            return am;
        }
    }

    public boolean existemembre(Groupe groupe, int idmembre) {
        existe = false;
        con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {
                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    nbmemberexiste = memberNumber(data);
                    if (nbmemberexiste == 0) {
                        existe = false;
                    }
                    if (nbmemberexiste == 1) {
                        Map<String, Object> obj = (Map<String, Object>) data.get("groupmembers");
                        if (Integer.parseInt(obj.get("member_id").toString()) == idmembre) {
                            existe = true;
                        }
                    }

                    if (nbmemberexiste > 1) {
                        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("groupmembers");
                        for (Map<String, Object> obj : list) {
                            if (Integer.parseInt(obj.get("member_id").toString()) == idmembre) {
                                existe = true;
                            }
                        }
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/existmembre.php?groupId=" + groupe.getId());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);

        if (existe == false) {
            return false;
        } else {
            return existe;
        }
    }

    public void addMemberToGroup(Groupe groupe, int id) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/addmemberGroup.php?userId=" + id + "&groupId=" + groupe.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            }

        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public void deleteMember(Groupe groupe, int id) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/deleteMember.php?userId=" + id + "&groupId=" + groupe.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            }
        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public int memberNumber(Map<String, Object> data) {
        String v = null;
        int nbm = 0;
        if (data.isEmpty()) {
            return 0;
        } else {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                Object value1 = entry.getValue();
                v = value1.toString();
            }

            StringTokenizer splitstring = new StringTokenizer(v, "}");
            nbm = splitstring.countTokens();
            return nbm;

        }
    }
    
    
    public ArrayList<ImageG> getGroupImage(Groupe groupe){
        ArrayList<ImageG> am = new ArrayList<>();

        con = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    nbimg = memberNumber(data);
                    if (nbimg == 0) {
                        ArrayList<User> am = new ArrayList<>();

                    }
                    if (nbimg == 1) {
                        Map<String, Object> obj = (Map<String, Object>) data.get("images");
                        ImageG img = new ImageG();
                        img.setId(Integer.parseInt(obj.get("id").toString()));
                        img.setName(obj.get("name").toString());
                        img.setMembre(new User(Integer.parseInt(obj.get("id_user").toString())));
                        img.setGroupe(new Groupe(Integer.parseInt(obj.get("id_groupe").toString())));
                        img.setDate_pub(obj.get("date_publication").toString());
                        am.add(img);
                    }

                    if (nbimg > 1) {
                        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("images");
                        for (Map<String, Object> obj : list) {
                        ImageG img = new ImageG();
                        img.setId(Integer.parseInt(obj.get("id").toString()));
                        img.setName(obj.get("name").toString());
                        img.setMembre(new User(Integer.parseInt(obj.get("id_user").toString())));
                        img.setGroupe(new Groupe(Integer.parseInt(obj.get("id_groupe").toString())));
                        img.setDate_pub(obj.get("date_publication").toString());
                        am.add(img);
                        }
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/getGroupImage.php?groupId=" + groupe.getId());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (am == null) {
            return null;
        } else {
            return am;
        }
    }
    
    
    public int groupeNumber(Map<String, Object> data) {
        String v = null;
        int nb = 0;
        if (data.isEmpty()) {
            return 0;
        }
        else{
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Object value1 = entry.getValue();
            v = value1.toString();
        }
       
        StringTokenizer splitstring = new StringTokenizer(v, "}");
        nb = splitstring.countTokens();
        return nb;

    }
    }

}
