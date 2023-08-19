package com.example.pickyourchoice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pickyourchoice.ui.fragments.LoginFragment;
import com.example.pickyourchoice.ui.fragments.SignUpFragment;

public class RegisterActivity extends AppCompatActivity {
    private Button login, signup;
    private ImageView close;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTheme(R.style.AppTheme_NoActionBar);

        if (getIntent().getExtras() != null) {
            String key = (String) getIntent().getExtras().get("key");
            switch (key) {
                case "login":
                    fragment = new LoginFragment();
                    //signup.setTextColor(R.color.gray);
                    break;
                case "signup":
                    fragment = new SignUpFragment();
                    // login.setTextColor(R.color.gray);
                    break;
            }
            loadFragment(fragment);
        }

        login = findViewById(R.id.login_fragment);
        signup = findViewById(R.id.signup_fragment);
        close = findViewById(R.id.close_fragment);

        login.setOnClickListener(v -> {
            if (!fragment.equals(new LoginFragment())) {
                loadFragment(new LoginFragment());
                //signup.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        signup.setOnClickListener(v -> {
            if (!fragment.equals(new SignUpFragment())) {
                loadFragment(new SignUpFragment());
                //login.setTextColor(getResources().getColor(R.color.gray));
            }
        });

        close.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        });

    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.register_container, fragment).commit();
        }
    }
}