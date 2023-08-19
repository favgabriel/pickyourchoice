package com.example.pickyourchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private ImageView profile_image;
    private TextView id_details;
    private FirebaseUser firebaseUser;
    private DatabaseReference userref;
    private Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Account");
        }

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        login = findViewById(R.id.login_button);
        signup = findViewById(R.id.signUp_button);
        profile_image = findViewById(R.id.profile_Image);
        id_details = findViewById(R.id.id_number);

        FirebaseAuth.getInstance().addAuthStateListener(firebaseAuth -> {
            if (firebaseAuth.getCurrentUser() != null) {

                userref = FirebaseDatabase.getInstance().getReference().child("users").child(firebaseUser.getUid());
                userref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String customerid = snapshot.child("customer_id").getValue().toString();
                            id_details.setText(customerid);
                            if (snapshot.hasChild("customer_img")) {
                                String image = snapshot.child("customer_img").getValue().toString();
                                Picasso.get().load(image).placeholder(R.drawable.no_image).into(profile_image);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                login.setText("Sign Out");
                signup.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void Login(View v) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, RegisterActivity.class).putExtra("key", "login"));
        } else {
            FirebaseAuth.getInstance().signOut();
        }
    }

    public void SignUp(View v) {
        startActivity(new Intent(this, RegisterActivity.class).putExtra("key", "signup"));
    }

    public void Orders(View v) {
        startActivity(new Intent(this, MainActivity.class).putExtra("key", "cart"));
    }

    public void Help(View v) {
        startActivity(new Intent(this,HelpActivity.class).putExtra("key","help"));
    }

    public void Settings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void Invite(View v) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = "get your fast foodies with ease click this link" +
                "\n, to download the pick your choice app";
        String url = " http://www.pickyourchoice.com/download";
        intent.putExtra(Intent.EXTRA_TEXT, text + url);
        startActivity(Intent.createChooser(intent, "Share via..."));

    }

    public void About(View v) {
        startActivity(new Intent(this,AboutActivity.class).putExtra("key","about"));
    }

    public void Partner(View v) {
        startActivity(new Intent(this,PartnerActivity.class).putExtra("key","partner"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}