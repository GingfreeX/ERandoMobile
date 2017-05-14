/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
public class ajouterpublication {

    Form f;
    String filePath;
    String Imagecode;

    public ajouterpublication(Resources theme) {
        UIBuilder uib = new UIBuilder();

        Container ctn1 = uib.createContainer(theme, "ajouterpublication");
        TextField tfMessage = (TextField) uib.findByName("description", ctn1);
        Button ajouterimage = (Button) uib.findByName("addimage", ctn1);
               TextField type = (TextField) uib.findByName("type", ctn1);

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

            request.setUrl("http://localhost/piService/temoignage/ajouterpub.php");
        
            request.addResponseListener(new ActionListener<NetworkEvent>() {

                @Override
                public void actionPerformed(NetworkEvent evt) {

                    byte[] data = (byte[]) evt.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                    if (s.equals("success")) {
                        Dialog.show("success", "publication ajoutée ", "Ok", null);
                        f.refreshTheme();
                    } else {
                        Dialog.show("success", "publication ajoutée", "Ok", null);
                    }
                }
            });
    NetworkManager.getInstance().addToQueueAndWait(request);
        });

        f = ctn1.getComponentForm();
            f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent evt) {
        affichertemoignange retour =  new affichertemoignange(theme);
        retour.getF().showBack();
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
