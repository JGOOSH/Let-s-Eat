package com.letseat.let_s_eat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener{

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
        listView.setOnItemClickListener(SearchActivity.this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selected = ((TextView) view.findViewById(R.id.name)).getText().toString();
        Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
        SearchActivity.this.startActivity(intent);
    }
}

