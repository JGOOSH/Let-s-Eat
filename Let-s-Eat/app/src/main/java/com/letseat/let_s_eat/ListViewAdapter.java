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

/**
 * Created by GOOSH on 2/17/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Names> namesList = null;
    private ArrayList<Names> arraylist;

    public ListViewAdapter(Context context, List<Names> namesList) {
        mContext = context;
        this.namesList = namesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Names>();
        this.arraylist.addAll(namesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return namesList.size();
    }

    @Override
    public Names getItem(int position) {
        return namesList.get(position);
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
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(namesList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        namesList.clear();
        if (charText.length() == 0) {
            namesList.addAll(arraylist);
        } else {
            for (Names wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    namesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
