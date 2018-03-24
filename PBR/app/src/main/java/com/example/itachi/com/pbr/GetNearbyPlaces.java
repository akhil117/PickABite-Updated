package com.example.itachi.com.pbr;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by akhilbatchu on 3/2/18.
 */

public class GetNearbyPlaces extends AsyncTask<Object,String,String>
{

    private GoogleMap mMap;
    String url;
    private String googlePlacesData;
    @Override
    protected String doInBackground(Object... objects) {


        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadUrl downloadURL = new DownloadUrl();
        try {
            googlePlacesData = downloadURL.readURl(url);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        return googlePlacesData;
           }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String ,String >> nearbyplaces;
        DataParser parser = new DataParser();
       nearbyplaces =  parser.parse(s);
        shownearbyplaces(nearbyplaces);
    }

    public void shownearbyplaces(List<HashMap<String,String>> hashMaps)
    {
        for(int i=0;i<hashMaps.size();i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String,String> map = hashMaps.get(i);
            String place = map.get("name");
            String vicinity = map.get("area");
            Double latitude = Double.parseDouble(map.get("lat"));
            Double longitude = Double.parseDouble(map.get("long"));
            String reference = map.get("ref");
            LatLng latLng = new LatLng(latitude,longitude);
             markerOptions.title("The different positions");
             markerOptions.position(latLng);
             markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));

             mMap.addMarker(markerOptions);
             mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
             mMap.animateCamera(CameraUpdateFactory.zoomBy(11));

        }
    }
}
