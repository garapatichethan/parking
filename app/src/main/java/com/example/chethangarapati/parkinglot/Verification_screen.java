package com.example.chethangarapati.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Verification_screen extends AppCompatActivity {

    private Button verlogin,veresend;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_screen);

        verlogin = findViewById(R.id.verButton1);
        veresend = findViewById(R.id.verbutton2);
        mAuth = FirebaseAuth.getInstance();

        verlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(Verification_screen.this , loginScreen.class);
                startActivity(loginIntent);


            }
        });

        veresend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getCurrentUser().sendEmailVerification();
                Toast.makeText(getApplicationContext(),"Verification email has been sent again",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
