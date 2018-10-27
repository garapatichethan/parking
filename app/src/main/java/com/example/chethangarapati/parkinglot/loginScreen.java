package com.example.chethangarapati.parkinglot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class loginScreen extends AppCompatActivity {

    private Button loginbtn,loginforgotpass;
    private EditText emailt,passwordt;
    private ProgressBar bar;
    private TextView taking;


    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        loginbtn = findViewById(R.id.Sign_in);
        emailt = findViewById(R.id.Emailsn);
        passwordt = findViewById(R.id.passwordsn);
        bar = findViewById(R.id.progressBar);
        taking = findViewById(R.id.takingin);
        loginforgotpass = findViewById(R.id.login_forgotpassword);

        Auth = FirebaseAuth.getInstance();
        bar.setVisibility(View.GONE);
        taking.setVisibility(View.GONE);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailt.getText().toString();
                String password = passwordt.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter E-mail",Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                }



                Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(loginScreen.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        bar.setVisibility(View.VISIBLE);
                        taking.setVisibility(View.VISIBLE);
                        if(Auth.getCurrentUser().isEmailVerified()) {
                            if (!task.isSuccessful()) {
                                bar.setVisibility(View.GONE);
                                taking.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Invalid E-mail/Password", Toast.LENGTH_SHORT).show();

                            } else {
                                bar.setVisibility(View.GONE);
                                taking.setVisibility(View.GONE);
                                Intent mainIntent = new Intent(loginScreen.this, MainScreen.class);
                                startActivity(mainIntent);
                            }
                        }
                        else{
                            bar.setVisibility(View.GONE);
                            taking.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"E-mail Not verified",Toast.LENGTH_SHORT).show();
                        }

                    }
                });





            }
        });

        loginforgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.sendPasswordResetEmail(emailt.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Password Reset Email Sent",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}
