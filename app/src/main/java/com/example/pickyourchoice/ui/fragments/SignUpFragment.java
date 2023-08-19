package com.example.pickyourchoice.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pickyourchoice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    private EditText email, password;
    private CheckBox checkBox;
    private Button signup;
    private FirebaseAuth mAuth;
    private DatabaseReference userref;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        email = view.findViewById(R.id.signup_email_address);
        password = view.findViewById(R.id.signup_password);
        checkBox = view.findViewById(R.id.rcheck);
        signup = view.findViewById(R.id.signup_btn);
        mAuth = FirebaseAuth.getInstance();
        userref = FirebaseDatabase.getInstance().getReference("users");


        signup.setOnClickListener(v -> {
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            if (!checkBox.isChecked()) {
                Toast.makeText(getContext(), "you must agree to the terms and conditions to register", Toast.LENGTH_SHORT).show();
                return;
            }
            /*if (!mail.matches("((A-Za-z0-9)(@)(A-Za-z)(.)(A-Za-z))")){
                Toast.makeText(getContext(),"please provide a valid email address",Toast.LENGTH_SHORT).show();
            }*/
            if (!mail.isEmpty() && !pass.isEmpty()) {
                int customerId = generateRandomId();
                mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    if (!Objects.equals(snapshot1.child("customer_id").getValue(Integer.class), customerId)) {
                                        userref.child(mAuth.getUid()).child("customer_id").setValue(customerId);
                                        Toast.makeText(getContext(), "successful! ", Toast.LENGTH_SHORT).show();
                                        getFragmentManager().beginTransaction().replace(R.id.register_container, new LoginFragment()).commit();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                }).addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getContext(), "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                    } else if (e instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getContext(), "This User Already Exists!", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getContext(), "please provide an email address and password! ", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private int generateRandomId() {
        int[] id = new int[8];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            id[i] = new Random().nextInt(9);
        }

        builder.append(Arrays.toString(id))
                .replace(builder.length() - 1, builder.length(), "")
                .replace(0, 1, "");

        return Integer.parseInt(String.valueOf(builder).replaceAll(", ", ""));
    }
}