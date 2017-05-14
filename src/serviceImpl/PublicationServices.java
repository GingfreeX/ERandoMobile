/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceImpl;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Publication;



/**
 *
 * @author cimope
 */
public class PublicationServices {
 
    public ArrayList<Publication> getListPublication(String json) {
        ArrayList<Publication> listPublication = new ArrayList<Publication>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> publications = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("publication");

            for (Map<String, Object> obj : list) {
                Publication p = new Publication();
                p.setId(Integer.parseInt(obj.get("id").toString()));
                p.setSection(obj.get("section").toString());
                p.setDatepub(obj.get("datepub").toString());
                p.setDescription(obj.get("description").toString());
                p.setIdUser(Integer.parseInt(obj.get("user_id").toString()));
    

   
                listPublication.add(p);

            }

        } catch (IOException ex) {
         }
        return listPublication;

    }   
}
