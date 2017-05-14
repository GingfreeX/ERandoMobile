/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Groupe;
import model.User;
import serviceImpl.GroupeService;
import serviceImpl.MembreService;
import com.codename1.maps.Coord;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

/**
 *
 * @author wassim
 */
public class MembreForm {

    MembreService ms = new MembreService();
    GroupeService gs = new GroupeService();
    Groupe g = gs.getGroupebyId(Groupe.getIdgrpconnected());
    User m = ms.getPubMember(User.getIdofuserAlreadyloggedin());
    EncodedImage encoded;
    Command back;
    Form f;
    int idcreateur = g.getCreateur().getId();
        private static final String HTML_API_KEY = "AIzaSyC9xCoKKqQ8-4S2B1bhLs6canc9_XRBQE0";


    public void displayMemberList(Form fhome, Resources theme) {

        try {
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            //Logger.getLogger(PublicationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        Command back = new Command("retour");

        Form member = new Form("Member");

        ArrayList<User> am = gs.getAllm();
        if (am.isEmpty()) {
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer im = new ImageViewer(theme.getImage("nodata.jpg"));
            c.add(im);
            member.add(c);

            member.show();
        } else {
            for (User i : am) {
                Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label lblSurnom = new Label(i.getUsername());
                ctn2.add(lblSurnom);

                lblSurnom.addPointerReleasedListener(ev -> {

                    //display detail
                    displayDetailMember(i, theme, member);

                });

                ctn2.setLeadComponent(lblSurnom);
                Image imguser = URLImage.createToStorage(encoded, i.getId() + i.getUsername(), "http://localhost/piService/" + i.getProfil_pic());

                ImageViewer imgProfile = new ImageViewer();
                imgProfile.setPreferredW(150);
                imgProfile.setPreferredH(150);
                imgProfile.setImage(imguser);

                ctn1.add(imgProfile);
                ctn1.add(ctn2);
                Button btndelete = new Button();
                btndelete.setVisible(false);
                btndelete.setUIID("btnd");
                btndelete.setIcon(theme.getImage("delete.png"));
                if (m.getId() == idcreateur) {
                    btndelete.setVisible(true);
                }
                // si user connecte n'est pas admin
                if (m.getId() != idcreateur) {
                    btndelete.setVisible(false);
                }
                btndelete.addActionListener(e -> {
                    gs.deleteMember(g, i.getId());
                    Dialog.show("Confirmation", "Membre Supprimé", "Ok", null);
                    displayMemberList(member, theme);

                });
                Container ctn3 = new Container();
                //ctn3.getAllStyles().setMarginLeft(15);
                ctn3.add(btndelete);
                ctn1.add(ctn3);
                member.add(ctn1);

            }
            member.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
                fhome.showBack();
            });
            member.show();

        }
    }

    public void displayDetailMember(User m, Resources theme, Form fo) {
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        UIBuilder uib = new UIBuilder();

        Container ctn = uib.createContainer(theme, "MemberDetail");
        Label lblnom = (Label) uib.findByName("lblnnom", ctn);
        Label lblprenom = (Label) uib.findByName("lblprenom", ctn);
        Label lblage = (Label) uib.findByName("lblage", ctn);
        Label lblmail = (Label) uib.findByName("lblmail", ctn);
        Label lblpays = (Label) uib.findByName("lblpays", ctn);
        TextField spnom = (TextField) uib.findByName("txtnom", ctn);
        TextField spprenom = (TextField) uib.findByName("txtprenom", ctn);
        TextField spage = (TextField) uib.findByName("txtage", ctn);
        TextField spmail = (TextField) uib.findByName("txtmail", ctn);
        TextField sppays = (TextField) uib.findByName("txtpays", ctn);
        ImageViewer imgprofil = (ImageViewer) uib.findByName("ImageViewer", ctn);

        final MapContainer cnt = new MapContainer();
        System.out.println(m.getPays());
        Coord cor = getCoords(m.getPays());
        cnt.zoom(cor,12);
        cnt.setCameraPosition(cor);
        
        try {
            cnt.addMarker(EncodedImage.create("/pin.png"),cor,m.getPays(),"",new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                }
            });
        } catch (IOException ex) {
            //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        spnom.setText(m.getNom());

        spprenom.setText(m.getPrenom());
        spage.setText(String.valueOf(m.getAge()));
        spmail.setText(m.getEmail());
        sppays.setText(m.getPays());
        Image imguser = URLImage.createToStorage(encoded, m.getId() + m.getUsername(), "http://localhost/piService/" + m.getProfil_pic());

        imgprofil.setPreferredW(250);
        imgprofil.setPreferredH(250);
        imgprofil.setImage(imguser);

        f = ctn.getComponentForm();
        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            fo.showBack();
        });
        f.add(cnt);
        f.show();

    }

    public void addMemberForm(String listamis, Resources theme, Form fhome) {
        Map<Integer, String> s = ms.getUsernamesWithId(listamis);
        List<String> ls = ms.getUsernameOnly(s);
        Image iback = theme.getImage("back.png");
        back = new Command("", iback);
        Form addm = new Form("Ajouter Membre", new BoxLayout(BoxLayout.Y_AXIS));
        addm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            fhome.showBack();
        });

        Container ctnadd = new Container();

        final DefaultListModel<String> options = new DefaultListModel<>(ls);

        AutoCompleteTextField auto = new AutoCompleteTextField(options);
        auto.setHint("Entrez un username ...");
        //auto.getAllStyles().setMarginTop(27);
        Image iconadd = theme.getImage("ajouter.png");
        Button btnadd = new Button();
        btnadd.setIcon(iconadd);
        btnadd.setUIID("btnadd");
        btnadd.addActionListener(e -> {
            int id = ms.getUsernameId(s, auto.getText());
            if (!"".equals(auto.getText())) {
                if (id != 0) {
                    if (!gs.existemembre(g, id)) {
                        gs.addMemberToGroup(g, id);
                        Dialog.show("Confirmation", "Membre ajouté avec succés", "Ok", null);
                        displayMemberList(addm, theme);

                    } else {
                        Dialog.show("Erreur", "Membre existe deja", "Ok", null);
                    }
                } else {
                    Dialog.show("Erreur", "Membre introuvable", "Ok", null);
                }
            } else {

                Dialog.show("Erreur", "champ vide", "Ok", null);

            }
        });

        ctnadd.add(auto).add(btnadd);
        auto.setMinimumElementsShownInPopup(5);

        addm.add(ctnadd);

        addm.show();
    }
    
    public static Coord getCoords(String address) {
        String MAPS_KEY = "AIzaSyC9xCoKKqQ8-4S2B1bhLs6canc9_XRBQE0";
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
