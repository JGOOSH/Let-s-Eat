package com.letseat.let_s_eat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener{

    //sample data to map UT Austin Campus to check whether API is working
    private final double UTLat = 30.2849;
    private final double UTLong = -97.7401;

    //variables
    ListView listView;
    ListViewAdapter adapter;
    SearchView editSearch;
    ArrayList<TestPlace> arrayPlaces;
    GooglePlacesAPIHandler placesHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        /*
        if(placesHandler == null) {
            try {
                placesHandler = new GooglePlacesAPIHandler(UTLat, UTLong);
            } catch (IOException e) {
                fail("API failed");
            }
        }
        */
        listView = (ListView) findViewById(R.id.view_searchList);
        //arraylist to hold data
        arrayPlaces = new ArrayList<TestPlace>();
        //load the sample data created my myself
        for(String name : getResources().getStringArray(R.array.array_name)){
         arrayPlaces.add(new TestPlace(name));
        }
        //now convert the data into items on the listview
        adapter = new ListViewAdapter(this, arrayPlaces);
        listView.setAdapter(adapter);
        //implementing click listener on listview items and query
        listView.setOnItemClickListener(SearchActivity.this);
        editSearch = (SearchView)findViewById(R.id.view_search);
        editSearch.setOnQueryTextListener(this);
    }

    @Override
    /*
    method called when the user submits the query
     */
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /*
    called when the query text is changed by the user.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        //update the query text
        String text = newText;
        //now update the list using the method implemented in listview adapter
        adapter.filter(text);
        return false;
    }
    /*
    method called when an item on the list is clicked
    it launches the search activity as well as passing the information of the place
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TestPlace data = adapter.getItem(position);
        String selected = data.getName();
        Toast toast = Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
        intent.putExtra("data", data);
        SearchActivity.this.startActivity(intent);
    }
}

