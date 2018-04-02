package com.example.itachi.com.pbr;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.List;

public class BillActivity extends AppCompatActivity implements AsyncResponse{

    Double Total;
    int flag1,flag2;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    public static final int req=123;
    public static Location currentlocation;

    private double total;
    TextView tv;
    int PROXIMITY_RADIUS = 10000;

    double latitude,longitude,end_latitude,end_longitude;
    String location;
    StringBuilder out;
    int i=0;
    public static String otu;
    private Button bt;
    private String message,email,subject,addressdelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        subject = "PickABiteNotification";
        email = "lifehacker2k18@gmail.com";

        Intent intent = getIntent();
       message = intent.getExtras().getString("message");
       addressdelivery = intent.getExtras().getString("addressofdelivery");
        bt =(Button)findViewById(R.id.payment) ;
        Total = intent.getExtras().getDouble("bill");
        tv = (TextView)findViewById(R.id.bill);
        flag1 = intent.getExtras().getInt("flag1");
        flag2 = intent.getExtras().getInt("flag2");
        location = intent.getExtras().getString("address");
       // Toast.makeText(getApplicationContext(),Total+","+flag1+","+flag2,Toast.LENGTH_LONG).show();
        if(flag1==1 && flag2==2)
        {
            ///only user had ordered both kinds of stuff///
            Location locatio = hello();
            end_latitude=9.177355;
            end_longitude=76.475270;
            latitude= locatio.getLatitude();
            longitude = locatio.getLongitude();
            callfunction();
        }
        else if(flag1==1)
        {
            ////only if user had ordered cakes///////
            Location locatio = hello();
            end_latitude=9.177355;
            end_longitude=76.475270;
            latitude= locatio.getLatitude();
            longitude = locatio.getLongitude();
            callfunction();
            char c='1';
            StringBuilder out=new StringBuilder("");
        }
        else
        {
            Location locatio = hello();
            end_latitude= 9.110409;
            end_longitude=76.527976;
            latitude= locatio.getLatitude();
            longitude = locatio.getLongitude();
            callfunction();
            char c='1';
            StringBuilder out=new StringBuilder("");
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////Pavan you should code here///////////////////////////
                /////////////pavan payment must be done///////////////////////
                /////////////pavan payment should be done from this button///////////
               SendMail sm = new SendMail(BillActivity.this, email, subject, message);
                //Executing sendmail to send email
               sm.execute();

            }
        });
    }
    public Location hello()
    {
        Object[] dataTransfer = new Object[2];
        GetNearbyPlaces getNearbyPlacesData = new GetNearbyPlaces();
        if(TextUtils.isEmpty(location))
        {
            Toast.makeText(getApplicationContext(),"Enter the address",Toast.LENGTH_SHORT).show();
        }
        List<Address> array = null ;
        MarkerOptions mo = new MarkerOptions();
        Geocoder geo = new Geocoder(this);
        try {
            array = geo.getFromLocationName(location, 5);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Location locatio;
        for(int i=0;i<array.size();i++) {
            Address ad = array.get(i);
            end_latitude = ad.getLatitude();
            end_longitude = ad.getLongitude();
            locatio = distance(end_latitude, end_longitude);
            LatLng latLng = new LatLng(ad.getLatitude(), ad.getLongitude());
            return  locatio;
        }
        return null;
    }
    public void callfunction()
    {
        Object[] dataTransfer = new Object[3];
        String url =getDirectionsUrl();
        getDirectionsData g = new getDirectionsData(getApplicationContext());
        g.delegate=this;
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;
        // dataTransfer[2] = new LatLng(end_latitude,end_longitude);
        g.execute(dataTransfer);
    }

    private String getDirectionsUrl()
    {
        StringBuilder googledirectionsurl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googledirectionsurl.append("origin="+latitude+","+longitude);
        googledirectionsurl.append("&destination="+end_latitude+","+end_longitude);
        googledirectionsurl.append("&key="+"AIzaSyBFKcDaAcYgc54NAZVIsgp8IJNBveY7Ibo");
        return  googledirectionsurl.toString();
    }
    public Location distance(double end_latitude,double end_longitude)
    {
        Location loc1 = new Location("");
        loc1.setLatitude(end_latitude);
        loc1.setLongitude(end_longitude);
        return loc1;
    }
    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyCpSlLNDyGqmRavcHAc9Uj5Dm17i2EIZhY");
        return googlePlaceUrl.toString();
    }
    public boolean checkLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED )
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION },req);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION },req);
            }
            return false;

        }
        else
            return true;
    }

    @Override
    public void processFinish(String output) {
        if (flag1 == 1 && flag2 == 2) {
            char c = '1';
            out = new StringBuilder("");
            while (c != 'k') {
                c = output.charAt(i);
                out.append(c);
                i++;
                c = output.charAt(i);
            }
            String a = out.toString();
           // Toast.makeText(getApplicationContext(),"d"+Total,Toast.LENGTH_SHORT).show();
            double percentage = Total /10;
           // Toast.makeText(getApplicationContext(),"m"+percentage,Toast.LENGTH_SHORT).show();
            double total = Double.parseDouble(a) + 17;
            double k = (total * 8) + Total+percentage;
            tv.setText("Rs"+k);
        }
        else if (flag1==1)
        {
            char c = '1';
            out = new StringBuilder("");
            while (c != 'k') {
                c = output.charAt(i);
                out.append(c);
                i++;
                c = output.charAt(i);
            }
            double percentage = Total /10;
            String a = out.toString();
            double total = Double.parseDouble(a);
            double k = (total * 8) + Total+percentage;
            tv.setText("Rs"+k);
        }
        else
        {
            char c = '1';
            out = new StringBuilder("");
            while (c != 'k') {
                c = output.charAt(i);
                out.append(c);
                i++;
                c = output.charAt(i);
            }
            String a = out.toString();
            double percentage = Total * (10/100);
            double total = Double.parseDouble(a);
            double k = (total * 8) + Total+percentage;
            tv.setText("Rs"+k);
        }
    }
}
