package com.example.kevin.mylistapplication;

import java.util.ArrayList;

/**
 * Created by kevin on 5/5/16.
 */
public class ItemStore {
    private ArrayList<String> stringArray;

    public ItemStore() {
        new ItemStore(0);
    }

    public ItemStore(int defaultItems) {
        stringArray = new ArrayList<String>();
        for (int i = 0; i < defaultItems; i++) {
            stringArray.add(String.format("Item %d", i));
        }
    }

    public void addItem(String item) {
        stringArray.add(item);
    }

    public void removeItem(int index) {
        stringArray.remove(index);

    }

    public void updateItem(String item, int index) {
        stringArray.remove(index);
        stringArray.add(index,item);
    }

    public ArrayList<String>items() {
        return stringArray;
    }

}
