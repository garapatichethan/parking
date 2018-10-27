package com.example.chethangarapati.parkinglot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainScreen extends AppCompatActivity {

    private ImageButton renterButton, granterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        renterButton = findViewById(R.id.renterButton);
        granterButton = findViewById(R.id.granterButton);

        renterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent rintent = new Intent(MainScreen.this , renterScreen.class);
                startActivity(rintent);
            }
        });

        granterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gintent = new Intent(MainScreen.this,granterScreen.class);
                startActivity(gintent);
            }
        });
    }
}
