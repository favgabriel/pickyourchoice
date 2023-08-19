package com.example.pickyourchoice;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pickyourchoice.ui.adapter.ViewFlipperAdapter;
import com.example.pickyourchoice.ui.model.ViewFlipperModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    DatabaseReference productref, userref;
    private TextView price, duration, description, name;
    private EditText quantity;
    private AdapterViewFlipper viewFlipper;
    private Button order;
    private TextView titlebar;
    private Toolbar toolbar;
    private String itemId;
    private FirebaseUser firebaseUser;
    private ArrayList<ViewFlipperModel> viewFlipperModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        populate();
        toolbar = findViewById(R.id.tool);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        duration = findViewById(R.id.duration);
        description = findViewById(R.id.descibe);
        quantity = findViewById(R.id.set_quantity);
        viewFlipper = findViewById(R.id.item_image_detail);
        order = findViewById(R.id.order_btn);
        titlebar = findViewById(R.id.items_title_name);
        if (getSupportActionBar() != null) {
            setSupportActionBar(toolbar);
        }
        order.setOnClickListener(v -> {

        });
        /* if the item is already ordered, set the order button to disable */
        if (getIntent().getExtras() != null) {
            itemId = (String) getIntent().getExtras().get("CARTID");
            //remove this line later
            itemId = "vdmgnjfnjjdfdf";
        }

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            userref.child(firebaseUser.getUid()).child("orders").child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        duration.setText(snapshot.child("duration").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            duration.setText("Undefined");
        }
        productref = FirebaseDatabase.getInstance().getReference().child("product");

        productref.child(itemId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        name.setText(snapshot1.child("name").getValue().toString());
                        description.setText(snapshot1.child("description").getValue().toString());
                        price.setText(snapshot1.child("price").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
/*
        if (name.getText() != null){
            titlebar.setText(name.getText());
        }*/

        viewFlipper.setAdapter(new ViewFlipperAdapter(getApplicationContext(), viewFlipperModelArrayList));
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(500);
    }

    private void populate() {
        viewFlipperModelArrayList.add(new ViewFlipperModel());
    }

    public void onBackPressed(View v) {
        super.onBackPressed();
    }
}