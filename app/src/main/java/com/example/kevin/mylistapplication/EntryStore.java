package com.example.kevin.mylistapplication;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by kevin on 5/5/16.
 */
public class EntryStore {

    private ArrayList<Entry> entries;
    private static EntryStore instance = null;

    protected EntryStore() {
        entries = new ArrayList<>();
    }

    public static EntryStore getInstance() {
        if(instance == null) {
            instance = new EntryStore();
        }
        return instance;
    }

    public void addItem(Entry entry) {
        entries.add(entry);
    }

    public void removeItem(Entry entry) {
        entries.remove(entry);
    }

    public Entry getItemWithId(String id) {
        for (Entry e : entries) {
            if(e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Entry getItemAtIndex(int index) {
        return entries.get(index);
    }

    public ArrayList<Entry>items() {
        return entries;
    }


    public void loadMockData() {
        // create a section
        Entry section1 = new Entry("Section 1", 1000);
        section1.isSection = true;
        this.entries.add(section1);

        // create some entries
        for (int i = 0; i < 5; i++) {
            Entry entry = new Entry("Name " + i, (i * 5));
            this.entries.add(entry);
        }

        // create another section
        Entry section2 = new Entry("Section 2", 999);
        section2.isSection = true;
        this.entries.add(section2);
        // create more entries
        for (int i = 0; i < 5; i++) {
            Entry entry = new Entry("Name" + i, (i * 10));
            this.entries.add(entry);
        }
    }
}
