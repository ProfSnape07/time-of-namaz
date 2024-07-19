package com.example.timeofnamaz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar2;
    FirebaseAuth fAuths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email1);
        mPassword = findViewById(R.id.password1);

        mLoginBtn = findViewById(R.id.login2);
        mCreateBtn = findViewById(R.id.newhere);
        fAuths = FirebaseAuth.getInstance();
        progressBar2 = findViewById(R.id.progressBar2);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email2 = mEmail.getText().toString().trim();
                String password2 = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email2)) {
                    mEmail.setError("E-mail is required.");
                    return;
                }
                if (TextUtils.isEmpty(password2)) {
                    mPassword.setError("Password is required.");
                    return;
                }
                if (password2.length() < 6) {
                    mPassword.setError("Password must be greater than six characters.");
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);

                fAuths.signInWithEmailAndPassword(email2, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressBar2.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
    }
}