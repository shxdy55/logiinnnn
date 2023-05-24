package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private EditText correo;
    private EditText contrasena;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onStart() {
        super.onStart();
        FirebaseUser currenUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    public void inciarSesion (View view) {
        mAuth.signInWithEmailAndPassword(correo.getText().toString().trim(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Autentication correcta",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Autentication fallida",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void irIniciar(View view){
        Intent i = new Intent(this,IniciarSesionActivity.class);
        startActivity(i);
    }
    public void irRegistrarse(View view) {
        Intent i = new Intent(this,RegistrarActivity.class);
        startActivity(i);
}
}