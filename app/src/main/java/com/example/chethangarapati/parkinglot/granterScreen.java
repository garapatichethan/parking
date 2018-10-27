package com.example.chethangarapati.parkinglot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class granterScreen extends AppCompatActivity {

    private Switch typehouse;
    private EditText datefield,parkingno;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granter_screen);

        typehouse = (Switch) findViewById(R.id.switchtype);
        datefield = (EditText) findViewById(R.id.date);
        parkingno = (EditText) findViewById(R.id.parkingno);


        typehouse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    parkingno.setVisibility(View.VISIBLE);
                }
                else{
                    parkingno.setVisibility(View.GONE);
                }
            }
        });

    }
}
