package com.example.pickyourchoice.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pickyourchoice.ItemsActivity;
import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.adapter.CategoryListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {
    ListView category_view;
    ArrayList<Model> category_list = new ArrayList<>();
    DatabaseReference categoryref, productref;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        populate();
        categoryref = FirebaseDatabase.getInstance().getReference().child("category");
        productref = FirebaseDatabase.getInstance().getReference().child("product");
        category_view = view.findViewById(R.id.categories_view);
        category_view.setAdapter(new CategoryListAdapter(getContext(), category_list));
        category_view.setOnItemClickListener(this);
        return view;
    }

    private void populate() {
        String[] cat = {"Food", "Drinks", "Snacks", "Fries"};
        ArrayList<String> list = new ArrayList<>();
        for (String c : cat) {
            switch (c) {
                case "Food":
                    list.clear();
                    categoryref.child("food").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list.add(snapshot.child("id").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    category_list.add(new Model(R.drawable.ic_food, "Food", list.size()));
                    break;
                case "Drinks":
                    list.clear();
                    categoryref.child("drinks").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list.add(snapshot.child("id").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    category_list.add(new Model(R.drawable.ic_food, "Drinks", list.size()));
                    break;
                case "Snacks":
                    list.clear();
                    categoryref.child("snacks").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list.add(snapshot.child("id").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    category_list.add(new Model(R.drawable.ic_food, "Snacks", list.size()));
                    break;
                case "Fries":
                    list.clear();
                    categoryref.child("fries").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            list.add(snapshot.child("id").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    category_list.add(new Model(R.drawable.ic_food, "Fries", list.size()));
                    break;
            }
        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ItemsActivity.class);
        intent.putExtra("CATEGORY", category_list.get(position).name);
        startActivity(intent);
    }

    public static class Model {
        public int drawable;
        public String name;
        public int quantity;

        public Model(int drawable, String name, int quantity) {
            this.drawable = drawable;
            this.name = name;
            this.quantity = quantity;
        }
    }
}