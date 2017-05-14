/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.Erandopi.Erando.PublicationT;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 *
 * @author student1
 */
public class detailspublication {
   
     Form f;
    String filePath;
    String Imagecode;
    public detailspublication(Resources theme,PublicationT p){
         UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "detailspub");
        TextField tfMessage = (TextField) uib.findByName("description", ctn1);
        Button ajouterimage = (Button) uib.findByName("addimage", ctn1);
         Button supprimer = (Button) uib.findByName("supprimer", ctn1);
               TextField type = (TextField) uib.findByName("type", ctn1);
               
               tfMessage.setText(p.getDescription());
        supprimer.addActionListener(x->{
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl("http://localhost/piService/temoignage/supprimerpub.php?id="+p.getId());
        
            request.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Dialog.show("success", "publication supprimée ", "Ok", null);
                        f.refreshTheme();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }
                }
            });
    NetworkManager.getInstance().addToQueueAndWait(request);
        
        
        });
        
               
        ajouterimage.addActionListener(x -> {
            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filePath = (String) e.getSource();

                }
            };
            if (FileChooser.isAvailable()) {
                FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);

            } else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }

        });
        Button ajouter = (Button) uib.findByName("Button", ctn1);
        ajouter.addActionListener(x -> {
            ImageIO imgIO = ImageIO.getImageIO();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                imgIO.save(Image.createImage(filePath), out, ImageIO.FORMAT_JPEG, 1);

                byte[] ba = out.toByteArray();
                Imagecode = Base64.encode(ba);
//System.out.println("data :"+Imagecode);

            } catch (IOException ex) {

            }
            int id = 17;
            ConnectionRequest request = new ConnectionRequest();
            request.setHttpMethod("POST");
            request.addArgument("Image", Imagecode);
            request.addArgument("iduser",""+id);
            request.addArgument("description", tfMessage.getText());
             request.addArgument("type",type.getText());
             request.addArgument("id",p.getId()+"");

            request.setUrl("http://localhost/piService/temoignage/updatepub.php");
        
            request.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Dialog.show("success", "publication mise à jour ", "Ok", null);
                        f.refreshTheme();
                    } else {
                        Dialog.show("Erreur", "erreur", "Ok", null);
                    }
                }
            });
    NetworkManager.getInstance().addToQueueAndWait(request);
        });

        f = ctn1.getComponentForm();
            f.getToolbar().addCommandToOverflowMenu("retour", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
    ajouterpublication m = new ajouterpublication(theme);
    m.getF().show();
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