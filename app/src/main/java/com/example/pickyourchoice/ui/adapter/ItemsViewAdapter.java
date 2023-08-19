package com.example.pickyourchoice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.model.ItemsModel;

import java.util.ArrayList;

public class ItemsViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemsModel> arrayList;

    public ItemsViewAdapter(Context context, ArrayList<ItemsModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.items_view, parent, false);
        }
        ImageView item_image;
        TextView item_name, item_price;

        item_image = convertView.findViewById(R.id.item_logo);
        item_name = convertView.findViewById(R.id.item_name);
        item_price = convertView.findViewById(R.id.item_price);

        item_name.setText(arrayList.get(position).getItem_name());
        item_price.setText(arrayList.get(position).getItem_price());
        // Picasso.get().load(arrayList.get(position).getImage()).into(item_image);
        return convertView;
    }
}
