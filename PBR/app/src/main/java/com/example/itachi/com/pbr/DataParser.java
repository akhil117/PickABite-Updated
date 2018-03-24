package com.example.itachi.com.pbr;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by akhilbatchu on 23/3/18.
 */

public class DataParser {

    private HashMap<String,String> getDuration(JSONArray googleDirectionjson)
    {
        HashMap<String,String> googledirection= new HashMap<>();
        String duration="";
        String distance="";
        Log.d("jsondata",googleDirectionjson.toString());
        try {
            duration = googleDirectionjson.getJSONObject(0).getJSONObject("duration").getString("text");
            distance  = googleDirectionjson.getJSONObject(0).getJSONObject("distance").getString("text");
            googledirection.put("duration",duration);
            googledirection.put("distance",distance);




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googledirection;
    }
    public HashMap<String,String> getPlace(JSONObject obj)
    {

        HashMap<String,String > googlePlacemap = new HashMap<>();
        String nameplace="NA";
        String vicinity="NA";

        String latitude=" ";
        String longitude=" ";
        String reference="";

        try
        {
            if(!obj.isNull("name"))
            {
                nameplace=obj.getString("name");
            }
            if(!obj.isNull("area"))
            {
                vicinity=obj.getString("area");
            }
            latitude = obj.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude=obj.getJSONObject("geometry").getJSONObject("location").getString("long");
            reference=obj.getString("ref");
            googlePlacemap.put("name",nameplace);
            googlePlacemap.put("area",vicinity);
            googlePlacemap.put("lat",latitude);
            googlePlacemap.put("long",longitude);
            googlePlacemap.put("ref",reference);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlacemap;
    }

    public List<HashMap<String,String>> place(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        List<HashMap<String,String>> places = new ArrayList<>();
        HashMap<String,String> hashMap=null;
        try
        {
            for(int i=0;i<count;i++) {
                hashMap = getPlace(jsonArray.getJSONObject(i));
                places.add(hashMap);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        return  places;
    }




    public HashMap<String,String> parseDirections(String jsondata)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsondata);
            jsonArray = jsonObject.getJSONArray("routes").getJSONObject(0).getJSONArray("legs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getDuration(jsonArray);
    }


    public List<HashMap<String, String>> parse(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;




        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place(jsonArray);
    }
}
