package com.example.itachi.com.pbr;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.payu.india.Model.PaymentParams;
import com.payu.india.Model.PayuConfig;
import com.payu.india.Model.PayuHashes;
import com.payu.india.Payu.Payu;
import com.payu.india.Payu.PayuConstants;
import com.payu.payuui.Activity.PayUBaseActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Checkout extends AppCompatActivity {
    private String totalamount;
    private int total;
    private String merchantKey, userCredentials;
    private PaymentParams mPaymentParams;
    private PayuConfig payuConfig;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mRef;
    private DatabaseReference mref;
    Button bt;
    Map<String, String> items;
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_checkout);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.pab_logo_org1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ///////////////////FIREBASE/////////////
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance();
        String uid = mAuth.getUid().toString();

        mref = mRef.getReference("Cart").child(uid);
        // bt = (Button)findViewById(R.id.hello);


        ///////////////////////////////////////
        Payu.setInstance(this);
        Intent intent = getIntent();
        TextView item = findViewById(R.id.itemname);
        item.setText(intent.getExtras().getString("name"));
        TextView cost = findViewById(R.id.itemcost);
        cost.setText("₹. " + intent.getExtras().getString("cost"));
        ImageView img = findViewById(R.id.finalimage);
        Picasso.with(this).load(intent.getExtras().getString("image"))
                .placeholder(R.drawable.progress_animation).into(img);

    }
    public void Checkout(View view)
    {
        Intent intent = new Intent(Checkout.this, addingitemstocart.class);
        startActivity(intent);
    }

    ////////////add to cart/////////////////////
    public void add(View view) {
        items = new HashMap<>();
        EditText q = (EditText) findViewById(R.id.quantit);


        if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(getApplicationContext(), "Please enter the Quantity", Toast.LENGTH_LONG).show();
            return;
        } else {

            Intent intent = getIntent();
            String cart = intent.getExtras().getString("ID");
            String name = intent.getExtras().getString("name");
            String cost = intent.getExtras().getString("cost");
            items.put(cart, quantity);
            Toast.makeText(getApplicationContext(), cart, Toast.LENGTH_SHORT).show();
            total = Integer.parseInt(quantity) * Integer.parseInt(cost.toString());
            totalamount = String.valueOf(total);
            String key = totalamount + name + cart;
            mref.child(key).child("rs").setValue(totalamount);
            mref.child(key).child("name").setValue(name);
            mref.child(key).child("id").setValue(cart);
            mref.child(key).child("quantity").setValue(quantity);
        }
    }
}
