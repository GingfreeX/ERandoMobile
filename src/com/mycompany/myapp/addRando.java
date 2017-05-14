/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ext.filechooser.FileChooser;
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
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.DateTimeSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.util.Base64;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aloulou
 */
public class addRando {

    private Resources theme;

    Form f;
    String filePath;
    String Imagecode;

   // private static final String apiKey = "AIzaSyBilfVJ4HcDcQcqcbkBeuQakGeCWZaOpgI";
    private static final String apiKey = " AIzaSyBch8rdmE7835My_AIHxdjrL5jKE75TmfI";
    
   




    public addRando(Resources theme) {

        f = new Form("Insert", BoxLayout.y());
        f.setUIID("addRando");
        listrando l = new listrando(theme);

        Validator v1 = new Validator();
        Validator v2 = new Validator();
        // Validator v3 = new Validator();
        Validator v4 = new Validator();
        //Validator v5 = new Validator();

        //Validator  = new Validator();
        Command c1 = new Command("Créer un randonnee");
        Command c2 = new Command("Gérer Randonnee");
        // Command c3 =new Command("liste_reservation");

        f.getToolbar().addCommandToSideMenu(c1);
        f.getToolbar().addCommandToSideMenu(c2);
        // f.getToolbar().addCommandToSideMenu(c3);

//            f.addCommandListener(e->{
//            if(e.getCommand()==c1){
//                addRando s = new addRando(theme);
//                s.getF().show();
//                   
//            }});
        f.addCommandListener(e -> {
            if (e.getCommand() == c2) {
                listrando list = new listrando(theme);

                list.getF().show();

            }

        });

        String s;
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
        ac.setMinimumElementsShownInPopup(5);

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
        
        Label lblTitre = new Label("Titre");
        TextField tfTitre = new TextField("", "Titre");
        v1.addConstraint(tfTitre, new LengthConstraint(5));

        Label lblDescription = new Label("Description");
        TextField tfDescription = new TextField("", "description");
        v2.addConstraint(tfDescription, new LengthConstraint(2));

        Label lblDestination = new Label("Destination");
        TextField tfDestination = new TextField("", "destination");

        Label lblDate = new Label("Date de départ");
        
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        //datePicker.set(new Date());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");
        datePicker.setFormatter(dateFormatter);

        // TextField tfDate = new TextField("", "date");
        Label lblPrix = new Label("Prix");
        TextField tfPrix = new TextField("", "prix");

        tfPrix.setConstraint(TextField.NUMERIC);
        //   v3.addConstraint(tfTitre, new Integer(0));

        //TextField tfImage = new TextField("", "image");
        Label lblPlace = new Label("nombre de places");
        TextField tfNbrePlace = new TextField("", "nbr_places");

        Label lblPointDepart = new Label("point de départ");
        TextField tfPointDepart = new TextField("", "point_depart");

        Label lblType = new Label("type de randonnee");
        TextField tftype = new TextField("", "type");

        Label lblNiveau = new Label("niveau de difficulte");
        TextField tfNiveau = new TextField("", "niveau");

        Label lblAgemin = new Label("age min");
        TextField tfAge_min = new TextField("", "age_min");

        Label lblTransport = new Label("moyen de transport");
        TextField tfTransport = new TextField("", "moyen_transport");
        v4.addConstraint(tfTransport, new LengthConstraint(2));

        ComboBox<Map<String, Object>> type = new ComboBox<>(
                createListEntry("Randonne", ""),
                createListEntry("camping", ""),
                createListEntry("chasse", "")
        );
//  combo.setRenderer(new GenericListCellRenderer<>     );
        type.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));

        ComboBox<Map<String, Object>> niveau = new ComboBox<>(
                createListEntry("1", "Trés facile"),
                createListEntry("2", "Trés facile"),
                createListEntry("3", "Trés facile"),
                createListEntry("4", "facile"),
                createListEntry("5", "facile"),
                createListEntry("6", "Difficile"),
                createListEntry("7", "Difficile"),
                createListEntry("8", "trés Difficile"),
                createListEntry("9", "trés Difficile")
               // createListEntry("10", "trés Difficile")
        );
//  combo.setRenderer(new GenericListCellRenderer<>     );
        niveau.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));

        f.add(lblTitre);
        f.add(tfTitre);

        f.add(lblDescription);
        f.add(tfDescription);

        f.add(lblDestination);
        f.add(ac);

        f.add(lblDate);
        f.add(datePicker);

        // f.add(tfDate);
        f.add(lblPrix);
        f.add(tfPrix);
        //     f.add(tfImage);

        f.add(lblPlace);
        f.add(tfNbrePlace);

        f.add(lblPointDepart);
        f.add(ac1);

        f.add(lblType);
        f.add(type);

        f.add(lblNiveau);
        f.add(niveau);

        f.add(lblAgemin);
        f.add(tfAge_min);

        f.add(lblTransport);
        f.add(tfTransport);
        Button ajouterimage = new Button("ajouterimage");
        
        ajouterimage.addActionListener(x -> {

            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filePath = (String) e.getSource();

                }
            };
            if (FileChooser.isAvailable()) {
               // FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
                 FileChooser.showOpenDialog(".gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);


            } else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }

        });
        Button btnOk = new Button("Insert");
        f.add(ajouterimage);
        f.add(btnOk);

        btnOk.addActionListener(x -> {

            ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                imgIO.save(Image.createImage(filePath), out, ImageIO.FORMAT_JPEG, 1);

                byte[] ba = out.toByteArray();
                Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);

            } catch (IOException ex) {
            }

            ConnectionRequest request = new ConnectionRequest();
            
            System.out.println("ffff" + niveau.getSelectedItem().get("Line1"));
//                Date d = new java.sql.Date(tfDate.getText());
            request.setPost(true);
            request.setHttpMethod("POST");
            request.addArgument("Image", Imagecode);
            request.addArgument("titre", tfTitre.getText());
            request.addArgument("description", tfDescription.getText());
            request.addArgument("destination", ac.getText());
            request.addArgument("prix", tfPrix.getText());
//request.addArgument("image",tfImage.getText()); 
            request.addArgument("nbr_places", tfNbrePlace.getText());
            request.addArgument("point_depart", ac1.getText());
            request.addArgument("type", (String) type.getSelectedItem().get("Line1"));
            request.addArgument("niveau", (String) niveau.getSelectedItem().get("Line1"));
            request.addArgument("age_min", tfAge_min.getText());
            request.addArgument("transport", tfTransport.getText());
           request.addArgument("date", datePicker.getText());
                      // request.addArgument("date", datePicker.getCurrentDate().toString());


            if (v1.isValid() && v2.isValid() && v4.isValid()) {
                request.setUrl("http://localhost/codenameone/insert.php");
                request.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);

                        String accountSid = "AC73f651b3a489419e84b5685c9977ca66" ;
                        String authToken = "fab93a0722f688b8fbd0a39b59e262f7";
                        Twilio.init(accountSid,authToken);
                        Message message = Message.creator(
                                new PhoneNumber("+21653486100"),
                               
                                new PhoneNumber("+18325868906"),
                                "ajout de rando avec succes"
                        ).create();
                        
                        
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "ajout ok", "Ok", null);

                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }

                    }
                });
                NetworkManager.getInstance().addToQueue(request);

            }
        });

    }

    private Map<String, Object> createListEntry(String name, String date) {
        //Map<String, Object> entry = new HashMap<>();
        Map< String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
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
                System.out.println(""+result);
                return res;
            }
        } catch (Exception err) {
            Log.e(err);
        }
        return null;
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
