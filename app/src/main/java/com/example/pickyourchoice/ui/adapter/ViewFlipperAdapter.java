package com.example.pickyourchoice.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.model.ViewFlipperModel;

import java.util.ArrayList;

public class ViewFlipperAdapter extends BaseAdapter {
    private final Context mcontext;
    private final ArrayList<ViewFlipperModel> images;

    public ViewFlipperAdapter(Context context, ArrayList<ViewFlipperModel> images) {
        this.mcontext = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.flipper_image_views, parent, false);
        }
        ViewFlipperModel views = images.get(position);

        android.widget.ImageView imageView = convertView.findViewById(R.id.image_container);
        //imageView.setImageResource(Integer.parseInt(views.getDrawableint()));
        return convertView;
    }
}
