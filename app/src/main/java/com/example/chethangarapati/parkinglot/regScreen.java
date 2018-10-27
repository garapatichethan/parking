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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class regScreen extends AppCompatActivity {

    public String oemail,opassword;

    private Button signup;
    private EditText emailt;
    private EditText passwordt,cnfrmpassword;
    private EditText phonenum;
    private EditText nickname;
    private ProgressBar bar;
    private TextView setting;
    private FirebaseAuth mAuth;


    private FirebaseDatabase database;
    private DatabaseReference ref;

    UserDetails userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_screen);

        signup = findViewById(R.id.signup);
        emailt = findViewById(R.id.Emailsn);
        passwordt = findViewById(R.id.passwordsp);
        cnfrmpassword = findViewById(R.id.Cnfm_passwordsp);
        phonenum = findViewById(R.id.phonenumt);
        nickname = findViewById(R.id.nickname);

        bar = findViewById(R.id.progressBar2);
        setting = findViewById(R.id.settingup);


        userinfo = new UserDetails();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User Details");

        bar.setVisibility(View.GONE);
        setting.setVisibility(View.GONE);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String email = emailt.getText().toString().trim();
                 String password = passwordt.getText().toString().trim();
                 String cnfpasswordstr = cnfrmpassword.getText().toString().trim();
                 String phnum = phonenum.getText().toString().trim();
                 String nName = nickname.getText().toString().trim();

                 oemail = email;
                 opassword = password;

               if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter E-mail",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!cnfpasswordstr.equals(password)){
                    Toast.makeText(getApplicationContext(),"Passwords Do not Match.Please Re-Enter the Password",Toast.LENGTH_SHORT).show();
                    passwordt.setText("");
                    cnfrmpassword.setText("");
                    return;
                }
                if(TextUtils.isEmpty(phnum)){
                    Toast.makeText(getApplicationContext(),"Enter Phone number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nName)){
                    Toast.makeText(getApplicationContext(),"Please Enter a Nick-Name",Toast.LENGTH_SHORT).show();
                    return;
                }

                String textview = "Creating account for "+ "\"" + nName + "\"";
                bar.setVisibility(View.VISIBLE);
                setting.setText(textview);
                setting.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(regScreen.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            bar.setVisibility(View.GONE);
                            setting.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"E-Mail Already Used",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            bar.setVisibility(View.GONE);
                            setting.setVisibility(View.GONE);

                            mAuth.getCurrentUser().sendEmailVerification();

                            Intent mainIntent = new Intent(regScreen.this , Verification_screen.class);
                            startActivity(mainIntent);
                        }
                    }
                });
                //sendemailverfi();
                updatedatebase();
            }
        });


    }

    public void sendemailverfi(){
        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Verification E-mail sent",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void updatedatebase(){

        String email = emailt.getText().toString().trim();
        String password = passwordt.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        final String UID = currentFirebaseUser.getUid().toString();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getvalues();
                ref.child(UID).setValue(userinfo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getvalues(){
        userinfo.setEmail(emailt.getText().toString());
        userinfo.setNickname(nickname.getText().toString());
        userinfo.setPhonenumber(phonenum.getText().toString());
    }

}
