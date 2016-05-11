package com.example.kevin.mylistapplication;

import java.util.UUID;

/**
 * Created by kevin on 5/11/16.
 */
public class Entry {
    String id;
    String name;
    int amount;

    public Entry(String name, int amount) {
        this.name = name;
        this.amount = amount;
        this.id = UUID.randomUUID().toString();
    }

}
