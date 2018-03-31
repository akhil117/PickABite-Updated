package com.example.itachi.com.pbr;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        if(isOnline()){
            setContentView(R.layout.activity_home);
        }
        else{
            setContentView(R.layout.activity_home);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logoutmenu)
        {
            mAuth.signOut();
            finish();
            return true;
        }
        if(id==R.id.cartmenu) {
            Intent intent = new Intent(Home.this, addingitemstocart.class);
            startActivity(intent);
            return  true;
        }
        return  true;

    }

    public void cakes_list(View view){
        Intent intent = new Intent(this, Catogiry.class);
        intent.putExtra("value",1);
        this.startActivity(intent);
    }
    public void snacks_list(View view){
        Intent intent = new Intent(this, Catogiry.class);
        intent.putExtra("value",3);
        this.startActivity(intent);
    }
    public void north_list(View view){
        Intent intent = new Intent(this, Catogiry.class);
        intent.putExtra("value",2);
        this.startActivity(intent);
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
