package serviceImpl;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.messages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cimope
 */
public class MessagesServices {
 public List<messages> getallmess(String json){
        ArrayList<messages> messagess = new ArrayList<messages>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> publications = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) publications.get("messages");
            for (Map<String, Object> obj : list) {
                
               messages p  = new messages();
                
                p.setIdsender(Integer.parseInt(obj.get("author_id").toString()));
                p.setIdreciver(Integer.parseInt(obj.get("addressee_id").toString()));
                p.setMessage(obj.get("messageText").toString());
               
    

   
                messagess.add(p);

            }

        } catch (IOException ex) {
         }
        return messagess;
 }   
}
