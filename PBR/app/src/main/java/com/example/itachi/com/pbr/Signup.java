package com.example.itachi.com.pbr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    TextView firstname,email,mno,pass;
    Button signup;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mRef;
    private DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance();
        firstname = (EditText) findViewById(R.id.fullName);
        email = (EditText) findViewById(R.id.userEmailId);
        mno = (EditText) findViewById(R.id.mobileNumber);
        pass = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signUpBtn);

    }
    public void signup(View view){
        mAuth.createUserWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(! task.isSuccessful()){
                            Toast.makeText(Signup.this,"Error in reistration try again",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(Signup.this,"Registerd successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Signup.this,Home.class));
                        }
                    }
                });
    }

    public void already_user(View view){
        startActivity(new Intent(Signup.this,LogIn.class));
    }

}
