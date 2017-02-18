package com.letseat.let_s_eat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener {

    //variables
    ListView listView;
    ListViewAdapter adapter;
    SearchView editSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listView = (ListView) findViewById(R.id.view_searchList);
        ArrayList<Names> arrayName = new ArrayList<Names>();
        for(String name : getResources().getStringArray(R.array.array_name)){
         arrayName.add(new Names(name));
        }
        adapter = new ListViewAdapter(this, arrayName);
        listView.setAdapter(adapter);

        editSearch = (SearchView)findViewById(R.id.view_search);
        editSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}

