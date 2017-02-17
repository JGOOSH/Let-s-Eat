package com.letseat.let_s_eat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchActivity extends Activity {

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView listView = (ListView)findViewById(R.id.view_searchList);
        ArrayList<String> arrayName = new ArrayList<>();
        arrayName.addAll(Arrays.asList(getResources().getStringArray(R.array.array_name)));

        adapter = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1, arrayName);
        listView.setAdapter(adapter);
        }
    }

