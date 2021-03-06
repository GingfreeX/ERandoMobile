package gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;
import model.Product;





/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class editProduct {

    private Form current;
    private Resources theme;
    private String fileName;
    private String filePath;
    private int productId;
    public editProduct(int id) {
    this.productId = id ;
    }
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        
        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
        
    }
    
    public void start() throws IOException {
        Form f = new Form("Informations de produit", new BorderLayout());
        
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage ajouterIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        FontImage modifierIcon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, s);
        FontImage afficherIcon = FontImage.createMaterial(FontImage.MATERIAL_DASHBOARD, s);
        FontImage exitIcon = FontImage.createMaterial(FontImage.MATERIAL_WARNING, s);
        Command cAjouter = new Command("Ajouter");
        Command cModifier = new Command("Modifier / Supprimer");
        Command cAfficher = new Command("Afficher");
        Command cExit = new Command("Exit");
        f.getToolbar().addCommandToSideMenu(cAjouter);
        f.getToolbar().addCommandToSideMenu(cModifier);
        f.getToolbar().addCommandToSideMenu(cAfficher);
        f.getToolbar().addCommandToSideMenu(cExit);
        Product p = getProduct(productId);
        ComboBox<Map<String, Object>> tfType = new ComboBox<> (
          "Randonnee",
          "peche","whatever..");
 
        f.addCommandListener(e->{
            if(e.getCommand()==cAjouter){
                new ProduitApplication().start(theme);
           
            }
            if(e.getCommand()==cModifier){
                try {
                    new editProducts().start();
                } catch (IOException ex) {
                }
            }
            if(e.getCommand()==cAfficher){
                try {
                    new showAll().start();
                } catch (IOException ex) {
                }
            }
            if(e.getCommand()==cExit){
                
            }
        });
            int mm = Display.getInstance().convertToPixels(3);
            final EncodedImage placeholder = EncodedImage.createFromImage(
            FontImage.createMaterial(FontImage.MATERIAL_SYNC, new Style()).
                scaled(300, 300), false);
            Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Image im = URLImage.createToStorage(placeholder, "image","http://localhost/piService/produit/"+p.getImage());
            ImageViewer v = new ImageViewer(im);
            ctn2.add(v);
            TextField titre = new TextField(p.getTitre());
            ctn2.add(titre);
            TextField description = new TextField(p.getDescription());
            ctn2.add(description);
            TextField prix = new TextField(""+p.getPrix());
            ctn2.add(prix);
         
            ctn2.add(tfType);
            Label dt = new Label(p.getDate());
            Container dateCtn = new Container();
            dateCtn.setLayout(new BorderLayout());
            dateCtn.add(BorderLayout.EAST,dt);
            ctn2.add(dateCtn);
            Button editButton = new Button("Edit");
            ctn2.add(editButton);
            //Listen to Edit Button
            editButton.addActionListener(e->{
                p.setTitre(titre.getText());
                p.setDescription(description.getText());
                p.setPrix(Float.parseFloat(prix.getText()));
                editProduct(p);
                });
            ctn2.setScrollableY(true);
            f.addComponent(BorderLayout.CENTER,ctn2);
            f.setScrollableY(true);
            f.show();
   
    }
    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }
     public Product getProduct(int id ) {
        Product am = new Product();

        ConnectionRequest con = new ConnectionRequest() {
            protected void readResponse(InputStream in) throws IOException {
                
                 JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");
                    Map<String, Object> data = json.parseJSON(reader);
                    Map<String, Object> obj = (Map<String, Object>) data.get("produit");
 
                        am.setId(Integer.parseInt(obj.get("id").toString()));
                        am.setTitre(obj.get("titre").toString());
                        am.setDescription(obj.get("description").toString());
                        am.setIdMember(Integer.parseInt(obj.get("idMembre").toString()));
                        am.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        am.setDate(obj.get("dateAdd").toString());
                        am.setImage(obj.get("imageName").toString());
                        am.setType(obj.get("type").toString());
                    
                } catch (IOException err) {
                    Log.e(err);
                }
            }
        };
        con.setUrl("http://localhost/piService/produit/getById.php?id="+id);
        con.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return am;
    }
     public void editProduct(Product p){
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/piService/produit/edit.php?titre=" + p.getTitre() + "&description=" + p.getDescription() + "&prix=" + p.getPrix()+"&id="+p.getId());
                
                req.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
                        if (s.equals("success")) {
                            Dialog.show("Confirmation", "success", "Ok", null);
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }
                    }
                });

                NetworkManager.getInstance().addToQueue(req);
            }
   
}
