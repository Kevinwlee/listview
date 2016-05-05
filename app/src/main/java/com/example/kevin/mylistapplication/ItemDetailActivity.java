package com.example.kevin.mylistapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent myIntent = getIntent(); // gets the previously created intent
        String item = myIntent.getStringExtra("itemKey");
        TextView textView = (TextView) findViewById(R.id.itemTextView);
        textView.setText(item);

    }
}
