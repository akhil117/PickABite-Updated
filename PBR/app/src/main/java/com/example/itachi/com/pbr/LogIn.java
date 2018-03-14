package com.example.itachi.com.pbr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView email,password;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LogIn.this, Home.class));
        }
    }

    //// logign in
    public void login(View view){
        email = (TextView) findViewById(R.id.emailid);
        password = (TextView) findViewById(R.id.pass);
        mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /// log in failed
                        if(! task.isSuccessful()){
                            if(password.getText().toString().length()< 6){
                                Toast.makeText( LogIn.this, "Password must be more than 6 charecters", Toast.LENGTH_LONG ).show();
                            }
                            else{
                                Toast.makeText( LogIn.this, "error loging in try again", Toast.LENGTH_LONG ).show();
                            }
                        }
                        //SUCCESSFUL LOGIN
                        else if(task.isSuccessful()){
                            startActivity(new Intent(LogIn.this,Home.class));
                        }
                    }
                });
    }
    public void signp(View view){
        startActivity(new Intent(LogIn.this,Signup.class));
    }
}
