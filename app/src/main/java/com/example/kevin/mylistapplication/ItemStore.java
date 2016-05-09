package com.example.kevin.mylistapplication;

import java.util.ArrayList;

/**
 * Created by kevin on 5/5/16.
 */
public class ItemStore {

    private ArrayList<String> stringArray;
    private static ItemStore instance = null;

    protected ItemStore() {
        stringArray = new ArrayList<>();
    }

    public static ItemStore getInstance() {
        if(instance == null) {
            instance = new ItemStore();
        }
        return instance;
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

    public String getItemAtIndex(int index) {
        return stringArray.get(index);
    }

    public ArrayList<String>items() {
        return stringArray;
    }

}
