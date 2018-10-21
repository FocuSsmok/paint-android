package com.example.przeb.paint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_panel extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView login;
    private TextView password;
    private FirebaseAuth.AuthStateListener authListener;
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_panel);
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(login_panel.this, paint_activity.class));
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    public void logIn(View view) {
        String mail = login.getText().toString();
        String pass = password.getText().toString();
        if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)){
            Toast.makeText(login_panel.this, "Pola są puste", Toast.LENGTH_LONG).show();
        }
        else {
            auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(login_panel.this, "Problem z zalogowaniem!", Toast.LENGTH_LONG).show();
                    }// else {
                       // startActivity(new Intent(login_panel.this, paint_activity.class));
                    //}
                }
            });
        }
    }
    public void register(View view) {
        startActivity(new Intent(login_panel.this, register_activity.class));
    }
}
