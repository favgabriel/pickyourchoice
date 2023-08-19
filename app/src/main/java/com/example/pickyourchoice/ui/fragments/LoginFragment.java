package com.example.pickyourchoice.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.pickyourchoice.ProfileActivity;
import com.example.pickyourchoice.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private EditText email, password;
    private Button login;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        email = view.findViewById(R.id.login_email_address);
        password = view.findViewById(R.id.login_password);

        login = view.findViewById(R.id.login_btn);
        login.setOnClickListener(this::onClick);
        return view;
    }

    private void onClick(View v) {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        if (!mail.isEmpty() && !pass.isEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getContext(), ProfileActivity.class));
                }
            }).addOnFailureListener(e -> {
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(getContext(), "Email or password is Incorrect!", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseAuthInvalidUserException) {
                    Toast.makeText(getContext(), "Invalid User!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "provide parameters for both email and password", Toast.LENGTH_SHORT).show();
        }
    }
}