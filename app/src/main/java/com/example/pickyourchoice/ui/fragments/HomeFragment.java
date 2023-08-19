package com.example.pickyourchoice.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pickyourchoice.R;
import com.example.pickyourchoice.ui.adapter.ItemListAdapter;
import com.example.pickyourchoice.ui.adapter.ViewFlipperAdapter;
import com.example.pickyourchoice.ui.model.ItemsModel;
import com.example.pickyourchoice.ui.model.ViewFlipperModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final ArrayList<ItemsModel> avail = new ArrayList<>(),
            recom = new ArrayList<>();
    private static final ArrayList<ViewFlipperModel> imageviews = new ArrayList<>();
    private AdapterViewFlipper viewFlipper;
    private RecyclerView available_view, recommended_view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        populate();
        available_view = view.findViewById(R.id.available_items);
        recommended_view = view.findViewById(R.id.recommended_items);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext()), layoutManager2 = new LinearLayoutManager(getContext());
        available_view.setLayoutManager(layoutManager1);
        recommended_view.setLayoutManager(layoutManager2);

        available_view.setHasFixedSize(true);
        recommended_view.setHasFixedSize(true);

        recommended_view.setAdapter(new ItemListAdapter(getContext(), recom));
        available_view.setAdapter(new ItemListAdapter(getContext(), avail));

        viewFlipper = view.findViewById(R.id.adapterViewFlipper);
        viewFlipper.setFlipInterval(500);
        viewFlipper.startFlipping();
        viewFlipper.setAdapter(new ViewFlipperAdapter(getContext(), imageviews));

        return view;
    }

    private void populate() {
        imageviews.add(new ViewFlipperModel(""));
    }

}