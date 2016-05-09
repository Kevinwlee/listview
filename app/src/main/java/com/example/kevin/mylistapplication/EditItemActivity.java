package com.example.kevin.mylistapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    private int index;
    private ItemStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent myIntent = getIntent();


        index = myIntent.getIntExtra("itemIndexKey", -1);
        store = ItemStore.getInstance();
        if (index != -1) {
            dataBind();
        }
    }

    protected void dataBind() {
        String item = store.getItemAtIndex(index);
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText(item);
    }


    public void cancelClicked(View view) {
        finish();
    }

    public void saveClicked(View view) {
        EditText editText = (EditText) findViewById(R.id.editText);
        store.updateItem(editText.getText().toString(), index);

        Context context = getApplicationContext();
        CharSequence text = "Item updated.";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();

        finish();

    }
}
