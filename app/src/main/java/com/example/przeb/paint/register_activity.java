package com.example.przeb.paint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_activity extends AppCompatActivity {
    private TextView email;
    private TextView password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        mAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
    }
    public void backToLoginPanel(View view) {
        startActivity(new Intent(register_activity.this, login_panel.class));
    }

    public void registerUser(View view) {
        String mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass)) {
            Toast.makeText(register_activity.this, "Pola są puste", Toast.LENGTH_LONG).show();
        } else {
                mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d("Logowanie ", "createUserWithEmail:success");
                            Toast.makeText(register_activity.this, "Dodano uzytkownika", Toast.LENGTH_LONG).show();
                        } else {
                            Log.w("Logowanie ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(register_activity.this, "Nazwa uzytkownika zajeta badz brak polaczenia z baza", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }

    }
}
