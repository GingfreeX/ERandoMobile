/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyListModel;
import java.util.ArrayList;
import model.Groupe;
import model.ImageG;
import model.Membre;
import serviceImpl.GroupeService;
import serviceImpl.MembreService;
import serviceImpl.PublicationService;

/**
 *
 * @author wassim
 */
public class ImageForm {
     GroupeService gs = new GroupeService();
    PublicationService ps = new PublicationService();
    MembreService ms = new MembreService();

    Membre m = ms.getPubMember(5);
    Groupe g = gs.getGroupebyId(2);
    public void displayImage(Resources theme){
        Form fimg = new Form("Photos", new BorderLayout());
        ArrayList<ImageG> imgList = gs.getGroupImage(g);

        String[] listdesimages = new String[imgList.size()];
        for (int i = 0; i < imgList.size(); i++) {
            listdesimages[i] = imgList.get(i).getName();
        }

        MyListModel mylis = new MyListModel(listdesimages);
        int ii = 0;
        for (ImageG i : imgList) {

           
            ImageViewer iv = new ImageViewer(mylis.getItemAtt(ii, i.getName()));
            iv.setImageList(mylis);

        
            fimg.add(BorderLayout.CENTER, iv);
            

            ii++;
        }
        fimg.show();
        
    }
    
}
