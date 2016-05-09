package com.example.kevin.mylistapplication;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ItemStore store;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = ItemStore.getInstance();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, store.items());

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        handleClicks(listView);
    }

    private void handleClicks(ListView view) {
        view.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        Intent detail = new Intent(MainActivity.this, ItemDetailActivity.class);

                        //passing the position, but would normally pass an id here.
                        detail.putExtra("itemIndexKey", position);
//                        startActivityForResult(detail,0);
                        startActivity(detail);

                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        store.addItem("test item");
        adapter.notifyDataSetChanged();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
