/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.SelectionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Amal
 */
public class ListModel implements com.codename1.ui.list.ListModel<Map<String, Object>> {

    int mm = Display.getInstance().convertToPixels(3);
    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
    Image icon1 = URLImage.createToStorage(placeholder, "icon1", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/GOTMTI2.jpg");
    Image icon2 = URLImage.createToStorage(placeholder, "icon2", "http://www.georgerrmartin.com/wp-content/uploads/2012/08/clashofkings.jpg");
    Image icon3 = URLImage.createToStorage(placeholder, "icon3", "http://www.georgerrmartin.com/wp-content/uploads/2013/03/stormswordsMTI.jpg");
    Image icon4 = URLImage.createToStorage(placeholder, "icon4", "http://www.georgerrmartin.com/wp-content/uploads/2012/08/feastforcrows.jpg");
    Image icon5 = URLImage.createToStorage(placeholder, "icon5", "http://georgerrmartin.com/gallery/art/dragons05.jpg");

    @Override
    public Map<String, Object> getItemAt(int index) {
        try {
            int idx = index % 7;
            switch (idx) {
                case 0:
                    return createListEntry("A Game of Thrones " + index, Image.createImage("/woman.jpg"));
                case 1:
                    return createListEntry("A Clash Of Kings " + index, Image.createImage("/woman.jpg"));
                case 2:
                    return createListEntry("A Storm Of Swords " + index, Image.createImage("/woman.jpg"));
                case 3:
                    return createListEntry("A Feast For Crows " + index, Image.createImage("/woman.jpg"));
                case 4:
                    return createListEntry("A Dance With Dragons " + index, Image.createImage("/woman.jpg"));
                case 5:
                    return createListEntry("The Winds of Winter " + index, Image.createImage("/woman.jpg"));
                default:
                    return createListEntry("A Dream of Spring " + index, Image.createImage("/woman.jpg"));
            }
        } catch (IOException ex) {
        }
        return null;
    }

    @Override
    public int getSize() {
        return 1000000;
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }

    @Override
    public void setSelectedIndex(int index) {
    }

    @Override
    public void addDataChangedListener(DataChangedListener l) {
    }

    @Override
    public void removeDataChangedListener(DataChangedListener l) {
    }

    @Override
    public void addSelectionListener(SelectionListener l) {
    }

    @Override
    public void removeSelectionListener(SelectionListener l) {
    }

    @Override
    public void addItem(Map<String, Object> item) {
    }

    @Override
    public void removeItem(int index) {
    }

    private Map<String, Object> createListEntry(String name, Object icon) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("icon", icon);
        return entry;
    }

}
