/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import model.Randonnee;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aloulou
 */
public class UpdateRando {

    private static final String apiKey = "AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI";
     Form f ;
     
   public  UpdateRando(Resources theme ){
          f = new Form("details", BoxLayout.y());
          f.setUIID("addRando");
  f.getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->{
  listrando l = new listrando(theme);
  l.getF().show();
  
  });
      f.show();
   }
    public void UpdateRando(Randonnee r,Resources theme) {
 
    
        System.out.println(r.getId());
        Label lblprix = new Label();
        Label lbldestination = new Label();
        Label lblnplace = new Label();
        Label lblptdep = new Label();
        Label lbltype = new Label();
        Label lblniv = new Label();
        Label lblage = new Label();
        Label lblmoyentran = new Label();
        Button modifier = new Button();
        Image imodif = theme.getImage("edit1.png");
        modifier.setIcon(imodif);
        modifier.setUIID("btnmodif");
        modifier.getAllStyles().setMarginLeft(500);
        modifier.setPreferredW(500);
          
        
        Button supprimer = new Button("");
        Image idelete = theme.getImage("delete1.png");
        supprimer.setIcon(idelete);
        supprimer.setUIID("btndelete");
         supprimer.getAllStyles().setMarginLeft(500);       
        

        if (apiKey == null) {
            f.add(new SpanLabel("This demo requires a valid google API key to be set in the constant apiKey, "
                    + "you can get this key for the webservice (not the native key) by following the instructions here: "
                    + "https://developers.google.com/places/web-service/get-api-key"));
            f.getToolbar().addCommandToRightBar("Get Key", null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
            f.show();
            return;
        }
        final DefaultListModel<String> options = new DefaultListModel<>();
        AutoCompleteTextField ac = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = searchLocations(text);
                if (l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }
        };


        AutoCompleteTextField ac1 = new AutoCompleteTextField(options) {
            @Override
            protected boolean filter(String text) {
                if (text.length() == 0) {
                    return false;
                }
                String[] l = searchLocations(text);
                if (l == null || l.length == 0) {
                    return false;
                }

                options.removeAll();
                for (String s : l) {
                    options.addItem(s);
                }
                return true;
            }
        };
        ac.setMinimumElementsShownInPopup(5);
        ac1.setMinimumElementsShownInPopup(5);

        TextField tfprix = new TextField();
        TextField tfdestination = new TextField();
        TextField tfnbplace = new TextField();
        TextField tfptdep = new TextField();
        TextField tfage = new TextField();
        TextField tfmoyentran = new TextField();

        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        datePicker.setFormatter(dateFormatter);

        datePicker.setText(r.getDate() + "");

        tfprix.setText(r.getPrix() + "");
      // ac.setText(r.getDestination());
        tfnbplace.setText(r.getNbr_places() + "");
       // ac1.setText(r.getPoint_depart());
        tfage.setText(r.getAge_min() + "");
        tfmoyentran.setText(r.getMoyen_transport());
        ComboBox<Map<String, Object>> cbtype = new ComboBox<>(
                createListEntry("Randonne"),
                createListEntry("camping"),
                createListEntry("chasse")
        );
        cbtype.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        ComboBox<Map<String, Object>> cbniveau = new ComboBox<>(
                createListEntry("1"),
                createListEntry("2"),
                createListEntry("3"),
                createListEntry("4"),
                createListEntry("5"),
                createListEntry("6"),
                createListEntry("7"),
                createListEntry("8"),
                createListEntry("9"),
                createListEntry("10")
        );
        cbniveau.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        // f.add();
        f.add(lblprix);
        f.add(tfprix);
        f.add(lbldestination);
        f.add(ac);
        f.add(lblnplace);
        f.add(tfnbplace);
        f.add(cbniveau);
        f.add(cbtype);
        f.add(lblage);
        f.add(tfage);
        f.add(lblptdep);
        f.add(ac1);
        f.add(lblmoyentran);
        f.add(tfmoyentran);
        f.add(datePicker);

        f.add(modifier);
        f.add(supprimer);

        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();

                req.setUrl("http://localhost/piService/guide/update.php?prix=" + tfprix.getText() + "&destination=" + ac.getText() + "&nbr_places=" + tfnbplace.getText() + "&age_min=" + tfage.getText() + "&transport=" + tfmoyentran.getText() + "&type=" + cbtype.getSelectedItem().get("Line1") + "&id=" + r.getId() + "&point_depart=" + ac1.getText() + "&date=" + datePicker.getText() + "&niveau=" + cbniveau.getSelectedItem().get("Line1"));

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        System.out.println("http://localhost/piService/guide/update.php?prix=" + tfprix.getText() + "&destination=" + tfdestination.getText() + "&nbr_places=" + tfnbplace.getText() + "&age_min=" + tfage.getText() + "&transport=" + tfmoyentran.getText() + "&type=" + cbtype.getSelectedItem().get("Line1") + "&id=" + r.getId() + "&niveau=" + "&point_depart=" + r.getPoint_depart() + "&niveau=" + cbniveau.getSelectedItem().get("Line1") + "&date=" + datePicker.getText());

                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "modif ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }

                    }
                });

                NetworkManager.getInstance().addToQueue(req);

            }
        });

        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/guide/delete.php?id=" + r.getId() + "");

                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "suppression ok", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }

                    }
                });

                NetworkManager.getInstance().addToQueue(req);

            }
        });

    

    }

    String[] searchLocations(String text) {
        try {
            if (text.length() > 0) {
                ConnectionRequest r = new ConnectionRequest();
                r.setPost(false);
                r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
                r.addArgument("key", apiKey);
                r.addArgument("input", text);
                NetworkManager.getInstance().addToQueueAndWait(r);
                Map<String, Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
                String[] res = Result.fromContent(result).getAsStringArray("//description");
                return res;
            }
        } catch (Exception err) {
            Log.e(err);
        }
        return null;
    }

    private Map<String, Object> createListEntry(String name) {
        //Map<String, Object> entry = new HashMap<>();
        Map< String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        // entry.put("Line2", date);
        return entry;
    }

}
