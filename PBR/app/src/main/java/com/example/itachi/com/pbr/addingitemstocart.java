package com.example.itachi.com.pbr;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class addingitemstocart extends AppCompatActivity {
    DatabaseReference db;
    List<Cart> cart;
    RecyclerView recyler;
    CartAdaptor cartadaptor;
    private String message;
    CardView cardview;
    private FirebaseAuth mAuth;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    public static final int req=123;
    public static Location currentlocation;
    EditText locations;
    private double total;
    private String Name;
    TextView tv;
    int PROXIMITY_RADIUS = 10000;
    Button bt;
    EditText alu,name;
    double latitude,longitude,end_latitude,end_longitude;
    public int flag1=0,flag2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingitemstocart);
        recyler = (RecyclerView)findViewById(R.id.recyclerView);
        locations = (EditText)findViewById(R.id.search);
        cart = new ArrayList<>();
        alu = (EditText)findViewById(R.id.addressofdelivery);

        mAuth = FirebaseAuth.getInstance();
        tv = (TextView)findViewById(R.id.textView2);
        bt=(Button)findViewById(R.id.payment);
        name=(EditText)findViewById(R.id.editTextssssss);

        String uid = mAuth.getUid().toString();
        db = FirebaseDatabase.getInstance().getReference("Cart").child(uid);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name =name.getText().toString();
                String address = locations.getText().toString();
                String addressofdelivery = alu.getText().toString();
               // Toast.makeText(getApplicationContext(),Name,Toast.LENGTH_SHORT).show();
                message = message+"\n"+"City:"+address+"\n"+"Address: "+addressofdelivery+"\n"+"Birth Day cake name: "+Name+"\n";
                if(TextUtils.isEmpty(address) && TextUtils.isEmpty(addressofdelivery) && TextUtils.isEmpty(Name))
                {
                    Toast.makeText(getApplicationContext(),"please fill the following details",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(addingitemstocart.this,BillActivity.class);
                intent.putExtra("bill",total);
                intent.putExtra("flag1",flag1);
                intent.putExtra("flag2",flag2);
                intent.putExtra("address",address);
                intent.putExtra("message",message );
                intent.putExtra("addressofdelivery",addressofdelivery);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cart.clear();
                message="";
                total=0.0;
                for(DataSnapshot d :dataSnapshot.getChildren())
                {
                    Cart a = d.getValue(Cart.class);
                    cart.add(a);
                    total = total + Double.parseDouble(a.getRs());
                    message = "The Food: "+a.getName()+"   "+"cost: "+a.getRs()+"   "+"Quantity: "+a.getQuantity()+"\n"+message;
                    Log.v("Total",total+"helo");
                    if(a.getId().equals("cakes"))
                    {
                        flag1=1;
                    }
                    if(a.getId().equals("north"))
                    {
                        flag2=2;
                    }
                    tv.setText("Rs:"+total);
                }
                cartadaptor = new CartAdaptor(addingitemstocart.this, cart);
                recyler.setLayoutManager(new LinearLayoutManager(addingitemstocart.this));
                recyler.hasFixedSize();
                recyler.setAdapter(cartadaptor);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}