package com.example.pickyourchoice;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pickyourchoice.ui.adapter.ItemsViewAdapter;
import com.example.pickyourchoice.ui.model.ItemsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    private GridView items;
    private Toolbar toolbar;
    private DatabaseReference productref;
    private TextView titlebar;
    private ArrayList<ItemsModel> arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        toolbar = findViewById(R.id.tool);
        titlebar = findViewById(R.id.items_title_name);
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
        }

        productref = FirebaseDatabase.getInstance().getReference().child("products");
        items = findViewById(R.id.items_view);
        String type = (String) getIntent().getExtras().get("CATEGORY");
        titlebar.setText(type + " Categories");
        populate(type);
        items.setAdapter(new ItemsViewAdapter(getApplicationContext(), arrayList));
    }

    private void populate(String type) {
        /* drom the firebase database use the type to get the categories column from the database*/
        productref.orderByChild("category").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        if (snapshot1.child("category").getValue().toString().equals(type)) {
                            String id = snapshot1.child("id").getValue().toString();
                            String name = snapshot1.child("name").getValue().toString();
                            String price = snapshot1.child("price").getValue().toString();
                            String image = snapshot1.child("image").getValue().toString();
                            ItemsModel model = new ItemsModel();
                            model.setId(id);
                            model.setItem_name(name);
                            model.setItem_price(price);
                            model.setImage(image);
                            arrayList.add(model);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onBackPressed(View v) {
        super.onBackPressed();
    }
}