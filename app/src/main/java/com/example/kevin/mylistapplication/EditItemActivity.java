package com.example.kevin.mylistapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    private String uuid;
    private Entry entry;
    private EntryStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent myIntent = getIntent();


        uuid = myIntent.getStringExtra("itemIndexKey");
        store = EntryStore.getInstance();
        if (uuid != null) {
            entry = store.getItemWithId(uuid);
            dataBind();
        }
    }

    protected void dataBind() {

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(entry.name);
    }


    public void cancelClicked(View view) {
        finish();
    }

    public void saveClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        entry.name = editText.getText().toString();

        Context context = getApplicationContext();
        CharSequence text = "Item updated.";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

        finish();

    }
}
