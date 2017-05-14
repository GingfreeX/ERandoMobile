/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.StringUtil;
import gui.PublicationForm;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import model.Groupe;
import model.User;
import model.PublicationGr;

/**
 *
 * @author wassim
 */
public class PublicationService {

    private ConnectionRequest con;
    Groupe g = new Groupe(Groupe.getIdgrpconnected());
    public int nbpub = 0;

    public void addpub(PublicationGr publication) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/insertPublication.php?userId=" + publication.getCreateur().getId() + "&groupId=" + publication.getGroupe().getId() + "&description=" + publication.getDescription() + "&nbJaime=" + publication.getNbrjaime());
        if (!publication.getPhoto().equals("\"\"")) {
        con.addArgument("image", publication.getPhoto());
        }else{
        con.addArgument("image","\"\"");
        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

               
            }
        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public void update(PublicationGr publication) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/updatePublication.php?pubId=" + publication.getId() + "&desc=" + publication.getDescription());
        System.out.println(con.getUrl());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                // System.out.println(s);
                if (("success").equals(s)) {
                    Dialog.show("Confirmation", "Publication Modifié", "Ok", null);
                } else {
                    Dialog.show("Erreur", "erreur", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public void delete(Integer id) {
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/deletePublication.php?pubId=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                // System.out.println(s);
                if (("success").equals(s)) {
                    Dialog.show("Confirmation", "Publication Supprimé", "Ok", null);
                } else {
                    Dialog.show("Erreur", "erreur", "Ok", null);
                }
            }
        });

        NetworkManager.getInstance().addToQueue(con);

    }

    public List<PublicationGr> getAll() {
        return null;
    }

    public ArrayList<PublicationGr> getListPub() {

        ArrayList<PublicationGr> lspub = new ArrayList<>();

        con = new ConnectionRequest() {

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    nbpub = publicationNumber(data);
                    if (nbpub == 0) {
                        ArrayList<PublicationGr> lspub = new ArrayList<>();

                    } 
                    if (nbpub == 1) {
                        Map<String, Object> obj = (Map<String, Object>) data.get("publication");
                        PublicationGr p = new PublicationGr();
                        p.setId(Integer.parseInt(obj.get("id").toString()));
                        p.setCreateur(new User(Integer.parseInt(obj.get("user_id").toString())));
                        p.setGroupe(new Groupe(Integer.parseInt(obj.get("group_id").toString())));
                        p.setDescription(obj.get("description").toString());
                        String date = (String) obj.get("datepub");
                        String substring = date.substring(0, 10);
                        p.setDate_publication(substring);
                        p.setPhoto(obj.get("photo").toString());
                        lspub.add(p);

                    } 
                    if (nbpub >1){
                        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) data.get("publication");

                        for (Map<String, Object> obj : list) {
                            PublicationGr p = new PublicationGr();
                            p.setId(Integer.parseInt(obj.get("id").toString()));
                            p.setCreateur(new User(Integer.parseInt(obj.get("user_id").toString())));
                            p.setGroupe(new Groupe(Integer.parseInt(obj.get("group_id").toString())));
                            p.setDescription(obj.get("description").toString());
                            String date = (String) obj.get("datepub");
                            String substring = date.substring(0, 10);
                            p.setDate_publication(substring);
                            p.setPhoto(obj.get("photo").toString());
                            lspub.add(p);
                        }
                    }

                } catch (IOException err) {
                    Log.e(err);
                }
            }

        };
        con.setUrl("http://localhost/piService/getPubByGroup.php?groupId=" + g.getId());
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        if (lspub == null) {
            return null;
        } else {
            for (PublicationGr i : lspub) {
                User m = new MembreService().getPubMember(i.getCreateur().getId());
                Groupe gs = new GroupeService().getGroupebyId(i.getGroupe().getId());
                i.setCreateur(m);
                i.setGroupe(gs);
            }

            return lspub;
        }

    }

    public int publicationNumber(Map<String, Object> data) {
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
