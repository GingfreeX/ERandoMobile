/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import model.ImageG;

/**
 *
 * @author wassim
 */
public class ImageService {
        private ConnectionRequest con;

    public void storeUploadImg(ImageG image){
        con = new ConnectionRequest();
        con.setUrl("http://localhost/piService/storeUploadImg.php?userId="+image.getMembre().getId()+"&groupId="+image.getGroupe().getId());
        con.addArgument("image",image.getName());
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {


                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);
                        System.out.println(s);
            }
        });

        NetworkManager.getInstance().addToQueue(con);
    }
    
}
