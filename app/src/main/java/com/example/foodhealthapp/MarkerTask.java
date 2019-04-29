package com.example.foodhealthapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MarkerTask extends AsyncTask<Void, Void, String> {

    private static final String LOG_TAG = "Food_Health";
    private static final String SERVICE_URL = "URL";

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            //throw new IOException("Error connecting to service", e); //uncaught
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return json.toString();
    }

    @Override
    protected void onPostExecute(String json) {
        try {
            // De-serialize the JSON string into an array of city objects
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                // Convert Strings to Double
                double lat = Double.parseDouble(jsonObj.getString("latitude"));
                double lng = Double.parseDouble(jsonObj.getString("longitude"));

                LatLng latLng = new LatLng(lat, lng);

                // Create a marker for each restaurant in the JSON data.
                MapsActivity.mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.rest_marker))
                        .snippet( "Cuisine: " + jsonObj.getString("cuisine") + "\n" + "Allergies: " + jsonObj.getString("allergies") + "\n" +
                                "Nutrition: " + jsonObj.getString("nutrition") + "\n" + "Calories: " + jsonObj.getString("calories"))
                        .title(jsonObj.getString("name"))
                        .position(latLng));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error processing JSON", e);
        }
    }
}
