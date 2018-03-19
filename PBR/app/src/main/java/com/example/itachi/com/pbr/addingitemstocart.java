package com.example.itachi.com.pbr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class addingitemstocart extends AppCompatActivity {

    DatabaseReference db;
    List<Cart> cart;
    RecyclerView recyler;
    CartAdaptor cartadaptor;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingitemstocart);
        recyler = (RecyclerView)findViewById(R.id.recyclerView);
        cart = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getUid().toString();
        db = FirebaseDatabase.getInstance().getReference("Cart").child(uid);

        db.addChildEventListener(new ChildEventListener() {
                                     @Override
                                     public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                         Cart a = dataSnapshot.getValue(Cart.class);
                                         Toast.makeText(getApplicationContext(),a.getName(),Toast.LENGTH_LONG).show();
                                         Log.v("Tags",a.getName());
                                         cart.add(a);
                                         cartadaptor = new CartAdaptor(addingitemstocart.this, cart);
                                         recyler.setLayoutManager(new LinearLayoutManager(addingitemstocart.this));
                                         recyler.hasFixedSize();
                                         recyler.setAdapter(cartadaptor);

                                     }

                                     @Override
                                     public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                     }

                                     @Override
                                     public void onChildRemoved(DataSnapshot dataSnapshot) {

                                     }

                                     @Override
                                     public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                     }

                                     @Override
                                     public void onCancelled(DatabaseError databaseError) {

                                     }
                                 }









        );




    }
}
