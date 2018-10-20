package spaceappsottawa.launchnow;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import spaceappsottawa.launchnow.adapters.GeneralRocketLaunchDataListViewAdapter;
import spaceappsottawa.launchnow.models.RocketLaunchListViewItem;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private final static String BASE_URL = "http://10.0.2.2:3001";

    private RequestQueue requestQueue;

    private ListView generalLaunchDataListView;

    private BaseAdapter generalRocketLaunchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        generalLaunchDataListView = (ListView) findViewById(R.id.general_launch_data_listview);

        retrieveRocketDataFromAPI();
    }

    public void retrieveRocketDataFromAPI() {
        String requestURL = "https://launchlibrary.net/1.4/launch/next/20";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        // Retrieve list of items that we are interested in from the JSON Array:
                        ArrayList<RocketLaunchListViewItem> listOfLaunches = retrieveDataFromResponse(response);
                        generalRocketLaunchAdapter = new GeneralRocketLaunchDataListViewAdapter(MainActivity.this, listOfLaunches);
                        generalLaunchDataListView.setAdapter(generalRocketLaunchAdapter);

                        generalLaunchDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                RocketLaunchListViewItem item = (RocketLaunchListViewItem) generalRocketLaunchAdapter.getItem(i);
                                // Toast.makeText(MainActivity.this, item.getMap_url(), Toast.LENGTH_SHORT).show();
                                String map_url = item.getMap_url().replace("MAP URL: ", "");
                                if (!map_url.equals("")) {
                                    // Create a Uri from an intent string. Use the result to create an Intent.
                                    Uri gmmIntentUri = Uri.parse(map_url);

                                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                                    // Attempt to start an activity that can handle the Intent
                                    // Toast.makeText(MainActivity.this, item.getMap_url(), Toast.LENGTH_SHORT).show();
                                    startActivity(mapIntent);

                                } else {
                                    Toast.makeText(MainActivity.this, "This launch does not have a Map URL!", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, error.toString());
            }
        });

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }

    public ArrayList<RocketLaunchListViewItem> retrieveDataFromResponse(String response) {
        ArrayList<RocketLaunchListViewItem> listOfLaunches = new ArrayList<RocketLaunchListViewItem>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.v(TAG, jsonObject.toString());

            JSONArray launchesJSONArray = jsonObject.getJSONArray("launches");
            Log.v(TAG, launchesJSONArray.toString());


            for (int i = 0; i < launchesJSONArray.length(); i++) {
                JSONObject temp_launch_json_object = launchesJSONArray.getJSONObject(i);
                listOfLaunches.add(new RocketLaunchListViewItem(temp_launch_json_object));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listOfLaunches;

    }

}
