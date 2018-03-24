package com.example.itachi.com.pbr;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by akhilbatchu on 7/3/18.
 */

public class getDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionData;
    static  String duration,distance;
    LatLng latLng;
    public AsyncResponse delegate;


    private static Context context;
    public getDirectionsData(Context c) {
        context = c;
    }

    protected String doInBackground(Object... objects) {



        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng =(LatLng) objects[2];

        DownloadUrl downloadURL = new DownloadUrl();
        try {
            googleDirectionData = downloadURL.readURl(url);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        return googleDirectionData;

    }
    @Override
    protected void onPostExecute(String s) {
        HashMap<String,String> directionlist =null;
        DataParser parser = new DataParser();
        directionlist = parser.parseDirections(s);
        duration = directionlist.get("duration");
        distance = directionlist.get("distance");

        //Toast.makeText(context,distance+"doneee",Toast.LENGTH_LONG).show();
        //Toast.makeText()
        delegate.processFinish(distance);



    }



}
