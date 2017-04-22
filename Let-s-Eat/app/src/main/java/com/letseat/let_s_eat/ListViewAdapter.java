package com.letseat.let_s_eat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.letseat.let_s_eat.R.id.place;

/**
 * Created by GOOSH on 2/17/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<TestPlace> testPlaceList = null;
    private ArrayList<TestPlace> arraylist;

    public ListViewAdapter(Context context, List<TestPlace> testPlaceList) {
        mContext = context;
        this.testPlaceList = testPlaceList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(testPlaceList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return testPlaceList.size();
    }

    @Override
    public TestPlace getItem(int position) {
        return testPlaceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            holder.name = (TextView) view.findViewById(place);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(testPlaceList.get(position).getName());
        return view;
    }

    /*
    method to update the list of items based on the query string input
     */
    public void filter(String charText) {
        //to ignore case
        charText = charText.toLowerCase(Locale.getDefault());
        //first empty the current list
        testPlaceList.clear();
        if (charText.length() == 0) {
            testPlaceList.addAll(arraylist);
        } else {
            for (TestPlace item : arraylist) {
                if (item.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    testPlaceList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

}
