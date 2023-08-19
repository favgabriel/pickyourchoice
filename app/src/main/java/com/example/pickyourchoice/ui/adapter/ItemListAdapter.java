package com.example.pickyourchoice.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickyourchoice.DetailsActivity;
import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.model.ItemsModel;

import java.util.ArrayList;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    Context context;
    ArrayList<ItemsModel> items;

    public ItemListAdapter(Context context, ArrayList<ItemsModel> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ViewHolder holder, int position) {
        holder.item_name.setText(items.get(position).getItem_name());
        holder.item_price.setText(items.get(position).getItem_price());
        // Picasso.get().load(items.get(position).getImage()).into(holder.item_image);
        holder.itemView.setOnClickListener(v -> context.startActivity(new Intent(context, DetailsActivity.class).putExtra("CARTID", items.get(position).getId())));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name, item_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.item_logo);
            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
        }
    }
}
