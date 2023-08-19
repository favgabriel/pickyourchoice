package com.example.pickyourchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pickyourchoice.ui.fragments.CartFragment;
import com.example.pickyourchoice.ui.fragments.CategoryFragment;
import com.example.pickyourchoice.ui.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView view;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.view);
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);


        if (getIntent().getExtras() != null) {
            String key = getIntent().getExtras().get("key").toString();
            if (key.equals("cart")) {
                loadFragment(new CartFragment());
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("Cart");
                }
            } else {
                loadFragment(new HomeFragment());
            }
        } else {
            loadFragment(new HomeFragment());
        }
        view.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.cart_menu:
                    fragment = new CartFragment();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle("Cart");
                    }
                    break;
                case R.id.categories_menu:
                    fragment = new CategoryFragment();
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle("Category");
                    }
                    break;
                case R.id.profile_menu:
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    break;
            }
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_home);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            loadFragment(new HomeFragment());
            Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        }
        return super.onOptionsItemSelected(item);
    }
}