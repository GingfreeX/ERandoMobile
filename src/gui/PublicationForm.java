/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import model.Groupe;
import model.ImageG;
import model.Membre;
import model.Publication;
import serviceImpl.GroupeService;
import serviceImpl.ImageService;
import serviceImpl.MembreService;
import serviceImpl.PublicationService;

/**
 *
 * @author wassim
 */
public class PublicationForm {

    MembreService ms = new MembreService();
    GroupeService gs = new GroupeService();
    PublicationService ps = new PublicationService();

    Membre m = ms.getPubMember(7);
    Groupe g = gs.getGroupebyId(2);
    String filePath = null;
    String fileName = "";
    String imgName = null;
    EncodedImage encoded;
    Command menuAdd, menuDelete, navigationPub, navigationMem, navigationPhoto;

    public PublicationForm() {
    }

    public void displayPublication(Resources theme) {
        Image ipub = theme.getImage("pub.png");
        Image imem = theme.getImage("team.png");
        Image iphoto = theme.getImage("pictures.png");
        Image iadd = theme.getImage("add.png");

        menuAdd = new Command("", iadd);
        navigationPub = new Command("Publication", ipub);
        navigationMem = new Command("Membres", imem);
        navigationPhoto = new Command("Photos", iphoto);
        String listamis = m.getListamis();

        try {
            encoded = EncodedImage.create("/load.png");
        } catch (IOException ex) {
            //Logger.getLogger(PublicationForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        UIBuilder uib = new UIBuilder();

        Container ctn = uib.createContainer(theme, "Publication");
        Form f = ctn.getComponentForm();
        f.getToolbar().addCommandToRightBar(menuAdd);
        f.addCommandListener(e -> {
            if (e.getCommand() == menuAdd) {

                new MembreForm().addMemberForm(listamis, theme, f);
            }
        });

        f.getToolbar().addCommandToSideMenu(navigationPub);
        f.addCommandListener(e -> {
            if (e.getCommand() == navigationPub) {
                new PublicationForm().displayPublication(theme);
            }
        });
        f.getToolbar().addCommandToSideMenu(navigationMem);
        f.addCommandListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getCommand() == navigationMem) {
                    //member form

                    new MembreForm().displayMemberList(f, theme);
                }
            }
        });
        f.getToolbar().addCommandToSideMenu(navigationPhoto);
        f.addCommandListener((ActionEvent ev) -> {
            if (ev.getCommand() == navigationPhoto) {

                new ImageForm().displayImage(theme);
            }

        });
        TextField txtpub = (TextField) uib.findByName("txtpub", ctn);
        Button btnpub = (Button) uib.findByName("btnpub", ctn);
        Button btnupload = (Button) uib.findByName("btnupload", ctn);
        btnupload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        if (ev != null && ev.getSource() != null) {
                            String filePath = (String) ev.getSource();
                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
                            imgName = filePath.substring(fileNameIndex);
                            fileName = filePath.substring(fileNameIndex);
                            Image imgg;
                            try {
                                imgg = Image.createImage(filePath);
                                if (imgg != null) {
                                    ImageIO imgIO = ImageIO.getImageIO();
                                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                                    imgIO.save(imgg, out, ImageIO.FORMAT_JPEG, 1);
                                    byte[] ba = out.toByteArray();
                                    fileName = Base64.encode(ba);
                                }
                            } catch (IOException ex) {
                            }

                        }
                    }
                }, Display.GALLERY_IMAGE);

            }
        });
        btnpub.getAllStyles().setMarginLeft(270);
        btnpub.setPreferredH(100);

        btnpub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fileName.equals("") && "".equals(txtpub.getText())) {
                    Publication p1 = new Publication(m, g, "\"\"", fileName, 0);
                    ps.addpub(p1);
                    ImageG img = new ImageG(fileName, g, m);
                    new ImageService().storeUploadImg(img);
                    displayPublication(theme);
                } else if (fileName.equals("") && !"".equals(txtpub.getText())) {
                    Publication p = new Publication(m, g, txtpub.getText(), "\"\"", 0);
                    ps.addpub(p);
                    displayPublication(theme);

                } else if (!fileName.equals("") && !"".equals(txtpub.getText())) {
                    Publication p = new Publication(m, g, txtpub.getText(), fileName, 0);
                    ps.addpub(p);
                    ImageG img = new ImageG(fileName, g, m);
                    new ImageService().storeUploadImg(img);
                    displayPublication(theme);

                } else {
                    Dialog.show("Erreur", "Publication vide", "ok", null);
                }
            }
        });

        ArrayList<Publication> ap = ps.getListPub();
        if (ap.isEmpty()) {
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            ImageViewer im = new ImageViewer(theme.getImage("nodata.jpg"));
            c.add(im);
            f.add(c);
            f.show();
        } else {
            for (Publication i : ap) {
                if (!"".equals(i.getPhoto()) && ("\"\"").equals(i.getDescription())) {
                    f.add(showOnlyImg(i, theme, f));
                    f.show();
                } else if (i.getPhoto().equals("\"\"") && !"".equals(i.getDescription())) {
                    f.add(showOnlyPub(i, theme, f));
                    f.show();

                } else if (!i.getPhoto().equals("\"\"") && !"".equals(i.getDescription())) {
                    f.add(showPubWithImg(i, theme, f));
                    f.show();

                }

            }
        }

        //ps.getAllPub(f, theme);
    }

    public Container showOnlyPub(Publication i, Resources theme, Form f) {
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctbtns = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbl1 = new Label(i.getDate_publication());
        TextField txtpubli = new TextField(i.getDescription());
        txtpubli.setUIID("Container");
        txtpubli.setEditable(false);
        txtpubli.getAllStyles().setMarginLeft(6);
        Label lbl3 = new Label(i.getCreateur().getUsername());
        Image icondelete = theme.getImage("delete.png");
        Button btnsup = new Button();
        btnsup.setUIID("btnsup");
        btnsup.getUnselectedStyle().setMarginLeft(25);
        btnsup.setIcon(icondelete);
        if (i.getCreateur().getId() != m.getId()) {
            btnsup.setVisible(false);
        }

        btnsup.addActionListener(e -> {
            ps.delete(i.getId());
            displayPublication(theme);

        });
        Image iconupdate = theme.getImage("Edit.png");
        Button btnupd = new Button();
        btnupd.setUIID("btnupd");
        btnupd.setIcon(iconupdate);
        if (i.getCreateur().getId() != m.getId()) {
            btnupd.setVisible(false);
        }
        btnupd.addActionListener(e -> {
            txtpubli.setEditable(true);
            txtpubli.setUIID("TextField");
            txtpubli.addActionListener(ev -> {
                Publication pub = new Publication(i.getId(), txtpubli.getText());
                ps.update(pub);
                displayPublication(theme);
                txtpubli.setUIID("Container");
                txtpubli.setEditable(false);

            });
        });

        Image imguser = URLImage.createToStorage(encoded, i.getCreateur().getEmail(), "http://localhost/piService/" + i.getCreateur().getProfil_pic());
        ImageViewer iv = new ImageViewer();
        iv.setPreferredW(110);
        iv.setPreferredH(110);
        iv.setImage(imguser);
        ctn1.add(iv);

        ctbtns.add(btnsup).add(btnupd);
        ctn4.add(lbl3);
        ctn4.add(lbl1);

        ctn3.add(txtpubli);

        ctn1.add(ctn4);
        ctn1.add(ctbtns);

        ctn2.add(ctn1);

        ctn2.add(ctn3);

        return ctn2;

    }

    public Container showPubWithImg(Publication i, Resources theme, Form f) {
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctbtns = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbl1 = new Label(i.getDate_publication());
        TextField txtpubli = new TextField(i.getDescription());
        txtpubli.setUIID("Container");
        txtpubli.setEditable(false);
        txtpubli.getAllStyles().setMarginLeft(6);
        Label lbl3 = new Label(i.getCreateur().getUsername());
        Image icondelete = theme.getImage("delete.png");
        Button btnsup = new Button();

        btnsup.setUIID("btnsup");
        btnsup.getUnselectedStyle().setMarginLeft(25);

        btnsup.setIcon(icondelete);
        if (i.getCreateur().getId() != m.getId()) {
            btnsup.setVisible(false);
        }

        btnsup.addActionListener(e -> {
            ps.delete(i.getId());
            displayPublication(theme);

        });
        Image iconupdate = theme.getImage("Edit.png");
        Button btnupd = new Button();
        btnupd.setUIID("btnupd");
        btnupd.setIcon(iconupdate);
        if (i.getCreateur().getId() != m.getId()) {
            btnupd.setVisible(false);
        }
        btnupd.addActionListener(e -> {
            txtpubli.setEditable(true);
            txtpubli.setUIID("TextField");
            txtpubli.addActionListener(ev -> {
                Publication pub = new Publication(i.getId(), txtpubli.getText());
                ps.update(pub);
                displayPublication(theme);
                txtpubli.setUIID("Container");
                txtpubli.setEditable(false);

            });
        });

        Image imguser = URLImage.createToStorage(encoded, i.getCreateur().getProfil_pic(), "http://localhost/piService/" + i.getCreateur().getProfil_pic());
        ImageViewer iv = new ImageViewer();
        iv.setPreferredW(110);
        iv.setPreferredH(110);
        iv.setImage(imguser);
        ctn1.add(iv);

        ctbtns.add(btnsup).add(btnupd);
        ctn4.add(lbl3);
        ctn4.add(lbl1);
        Image imgpub = URLImage.createToStorage(encoded, i.getPhoto(), "http://localhost/piService/" + i.getPhoto());
        ImageViewer img = new ImageViewer();
        img.setPreferredW(800);
        img.setPreferredH(600);
        img.setImage(imgpub);

        ctn3.add(txtpubli);

        ctn3.add(img);
        ctn1.add(ctn4);
        ctn1.add(ctbtns);

        ctn2.add(ctn1);

        ctn2.add(ctn3);

        return ctn2;

    }

    public Container showOnlyImg(Publication i, Resources theme, Form f) {
        Container ctn1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container ctn2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctn4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container ctn3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container ctbtns = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label lbl1 = new Label(i.getDate_publication());
        TextField txtpubli = new TextField();
        txtpubli.setUIID("Container");
        txtpubli.setEditable(false);
        txtpubli.getAllStyles().setMarginLeft(6);
        Label lbl3 = new Label(i.getCreateur().getUsername());
        Image icondelete = theme.getImage("delete.png");
        Button btnsup = new Button();
        btnsup.setUIID("btnsup");
        btnsup.getUnselectedStyle().setMarginLeft(25);

        btnsup.setIcon(icondelete);
        if (i.getCreateur().getId() != m.getId()) {
            btnsup.setVisible(false);
        }

        btnsup.addActionListener(e -> {
            ps.delete(i.getId());
            displayPublication(theme);

        });
        Image iconupdate = theme.getImage("Edit.png");
        Button btnupd = new Button();
        btnupd.setUIID("btnupd");
        btnupd.setIcon(iconupdate);
        if (i.getCreateur().getId() != m.getId()) {
            btnupd.setVisible(false);
        }
        btnupd.addActionListener(e -> {
            txtpubli.setEditable(true);
            txtpubli.setUIID("TextField");
            txtpubli.addActionListener(ev -> {
                Publication pub = new Publication(i.getId(), txtpubli.getText());
                ps.update(pub);
                displayPublication(theme);
                txtpubli.setUIID("Container");
                txtpubli.setEditable(false);

            });
        });

        Image imguser = URLImage.createToStorage(encoded, i.getCreateur().getProfil_pic(), "http://localhost/piService/" + i.getCreateur().getProfil_pic());
        ImageViewer iv = new ImageViewer();
        iv.setPreferredW(110);
        iv.setPreferredH(110);
        iv.setImage(imguser);
        ctn1.add(iv);

        ctbtns.add(btnsup).add(btnupd);
        ctn4.add(lbl3);
        ctn4.add(lbl1);
        Image imgpub = URLImage.createToStorage(encoded, i.getPhoto(), "http://localhost/piService/" + i.getPhoto());
        ImageViewer img = new ImageViewer();
        img.setPreferredW(800);
        img.setPreferredH(600);
        img.setImage(imgpub);

        ctn3.add(txtpubli);

        ctn3.add(img);
        ctn1.add(ctn4);
        ctn1.add(ctbtns);

        ctn2.add(ctn1);

        ctn2.add(ctn3);

        return ctn2;

    }
}
