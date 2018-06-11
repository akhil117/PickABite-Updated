package com.example.itachi.com.pbr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Catogiry extends AppCompatActivity {
    private RecyclerView recyclerview;
    private  ListViewAdaptor adaptor;
    ArrayList<Cakes> info;
    private final Context context = this;
    DatabaseReference mref,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getSupportActionBar().setLogo(R.drawable.pab_logo_org1);

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_catogiry);

        Intent intent = getIntent();
        recyclerview = (RecyclerView) findViewById(R.id.listrecyclerview);
        /////////////////////////////////////////////////******FIREBASE******************///\\\\\\\\\\\\\\\\\\\\\\///////////
        info = new ArrayList<>();



        if(intent.getExtras().getInt("value",0)==1){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
          getSupportActionBar().setLogo(R.drawable.pab_logo_org1);

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            mref = FirebaseDatabase.getInstance().getReference("Cakes");
            //about = FirebaseDatabase.getInstance().getReference("About");
            mref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Cakes a = dataSnapshot.getValue(Cakes.class);
                    //Log.v("data","added");

                    info.add(a);

                    ///////////////////////******************setting up the adapter***********////////////////////////////

                    adaptor = new ListViewAdaptor(context , info);
                    recyclerview.setLayoutManager(new LinearLayoutManager(context));
                    recyclerview.hasFixedSize();
                    recyclerview.setAdapter(adaptor);
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
            });
        }
        else if(intent.getExtras().getInt("value",0)==3){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.pab_logo_org1);

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            mref = FirebaseDatabase.getInstance().getReference("Snacks");
            //about = FirebaseDatabase.getInstance().getReference("About");
            mref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Cakes a = dataSnapshot.getValue(Cakes.class);
                    //Log.v("data","added");

                    info.add(a);

                    ///////////////////////******************setting up the adapter***********////////////////////////////

                    adaptor = new ListViewAdaptor(context , info);
                    recyclerview.setLayoutManager(new LinearLayoutManager(context));
                    recyclerview.hasFixedSize();
                    recyclerview.setAdapter(adaptor);
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
            });
        }
        else if(intent.getExtras().getInt("value",0)==2){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.drawable.pab_logo_org1);

            getSupportActionBar().setDisplayUseLogoEnabled(true);
            mref = FirebaseDatabase.getInstance().getReference("Northy");
            //about = FirebaseDatabase.getInstance().getReference("About");
            mref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Cakes a = dataSnapshot.getValue(Cakes.class);
                    //Log.v("data","added");

                    info.add(a);

                    ///////////////////////******************setting up the adapter***********////////////////////////////

                    adaptor = new ListViewAdaptor(context , info);
                    recyclerview.setLayoutManager(new LinearLayoutManager(context));
                    recyclerview.hasFixedSize();
                    recyclerview.setAdapter(adaptor);
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
            });
        }
    }


}
