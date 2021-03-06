package com.example.kevin.mylistapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailActivity extends AppCompatActivity {

    private int index;
    private ItemStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent myIntent = getIntent();
        index = myIntent.getIntExtra("itemIndexKey", -1);
        store = ItemStore.getInstance();
        if (index != -1) {
            dataBind();
        }
    }

    private void dataBind() {
        String item = store.getItemAtIndex(index);
        TextView textView = (TextView) findViewById(R.id.itemTextView);
        textView.setText(item);
    }

    public void onDelete(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        store.removeItem(index);

                        Context context = getApplicationContext();
                        CharSequence text = "Item deleted.";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();

                        finish();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        Log.d("LOG", "on cancel");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void onEdit(View view) {
        Intent detail = new Intent(this, EditItemActivity.class);
        detail.putExtra("itemIndexKey", index);
        startActivity(detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataBind();
    }

    public void cancelClicked(View view) {
        finish();
    }
}
