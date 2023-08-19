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
import com.example.pickyourchoice.ui.model.CartModel;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModel> arrayList;

    public CartListAdapter(Context context, ArrayList<CartModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.cart_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartModel model = arrayList.get(position);

        /*i have not added image yet*/
        // Picasso.get().load(model.getItemimage()).into(itemimg);
        holder.itemname.setText(model.getItemname());
        holder.itemprice.setText(model.getItemprice());
        holder.itemquantity.setText(model.getItemquantity());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemimg;
        TextView itemname;
        TextView itemprice;
        TextView itemquantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemimg = itemView.findViewById(R.id.cart_item_image);
            itemname = itemView.findViewById(R.id.cart_item_name);
            itemprice = itemView.findViewById(R.id.cart_item_price);
            itemquantity = itemView.findViewById(R.id.cart_item_quantity);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
            intent.putExtra("CARTID", arrayList.get(getAdapterPosition()).getId());
            itemView.getContext().startActivity(intent);
        }
    }
}
