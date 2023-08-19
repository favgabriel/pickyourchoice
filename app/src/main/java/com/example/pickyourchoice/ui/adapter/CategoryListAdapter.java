package com.example.pickyourchoice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.fragments.CategoryFragment;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {
    Context context;
    ArrayList<CategoryFragment.Model> arrayList;

    public CategoryListAdapter(Context context, ArrayList<CategoryFragment.Model> arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.category_view, parent, false);
        }
        CategoryFragment.Model model = arrayList.get(position);
        ImageView logo = convertView.findViewById(R.id.category_logo);
        TextView name = convertView.findViewById(R.id.category_name);
        TextView quantity = convertView.findViewById(R.id.category_quantity);

        logo.setImageResource(model.drawable);
        name.setText(model.name);
        quantity.setText(String.valueOf(model.quantity));

        return convertView;
    }
}
