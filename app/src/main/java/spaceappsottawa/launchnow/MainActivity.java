package spaceappsottawa.launchnow;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import spaceappsottawa.launchnow.models.Launch;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private final static String TAG = "MainActivity";

    private RequestQueue requestQueue;

    private ListView generalLaunchDataListView;

    private GeneralRocketLaunchDataListViewAdapter generalRocketLaunchAdapter;
    private MenuItem searchMenuItem;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        generalLaunchDataListView = (ListView) findViewById(R.id.general_launch_data_listview);

        retrieveLaunchDataFromAPI();
    }

    public void retrieveLaunchDataFromAPI() {
        String requestURL = "https://launchlibrary.net/1.4/launch/next/20";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        // Retrieve list of items that we are interested in from the JSON Array:
                        ArrayList<Launch> listOfLaunches = retrieveLaunchDataFromResponse(response);
                        generalRocketLaunchAdapter = new GeneralRocketLaunchDataListViewAdapter(MainActivity.this, listOfLaunches);
                        generalLaunchDataListView.setAdapter(generalRocketLaunchAdapter);

                        generalLaunchDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Launch launchItem = (Launch) generalRocketLaunchAdapter.getItem(i);
                                Intent intent = new Intent(MainActivity.this, DetailedLaunchActivity.class);
                                Bundle mBundle = new Bundle();
                                mBundle.putString("launch_name", launchItem.getName());
                                mBundle.putString("launch_window_start", launchItem.getWindowStart());
                                mBundle.putString("launch_window_end", launchItem.getWindowEnd());
                                mBundle.putInt("launch_status", launchItem.getStatus());
                                mBundle.putString("launch_video_url", launchItem.getVidURLs());

                                if (launchItem.getLocation() == null) {
                                    mBundle.putBoolean("location_exists", false);
                                } else {
                                    mBundle.putBoolean("location_exists", true);
                                    mBundle.putString("location_name", launchItem.getLocation().getName());
                                    mBundle.putString("location_country", launchItem.getLocation().getCountryCode());
                                    mBundle.putString("location_pad_name", launchItem.getLocation().getPadName());
                                    mBundle.putString("location_pad_map_url", launchItem.getLocation().getPadMapURL());
                                    mBundle.putString("location_agency", launchItem.getLocation().getPadAgencyName());
                                }

                                if (launchItem.getRocket() == null) {
                                    mBundle.putBoolean("rocket_exists", false);
                                } else {
                                    mBundle.putBoolean("rocket_exists", true);
                                    mBundle.putString("rocket_name", launchItem.getRocket().getName());
                                    mBundle.putString("rocket_wiki_url", launchItem.getRocket().getWikiURL());
                                    mBundle.putString("rocket_image_url", launchItem.getRocket().getImageURL());
                                }

                                if (launchItem.getMission() == null) {
                                    mBundle.putBoolean("mission_exists", false);
                                } else {
                                    mBundle.putBoolean("mission_exists", true);
                                    mBundle.putString("mission_name", launchItem.getMission().getName());
                                    mBundle.putString("mission_description", launchItem.getMission().getDescription());
                                    mBundle.putString("mission_wiki_url", launchItem.getMission().getWikiURL());
                                    mBundle.putString("mission_type_name", launchItem.getMission().getTypeName());
                                    mBundle.putStringArrayList("mission_payloads", launchItem.getMission().getPayloads());
                                }

                                if (launchItem.getLaunchServiceProvider() == null) {
                                    mBundle.putBoolean("lsp_exists", false);
                                } else {
                                    mBundle.putBoolean("lsp_exists", true);
                                    mBundle.putString("lsp_name", launchItem.getLaunchServiceProvider().getName());
                                    mBundle.putString("lsp_wiki_url", launchItem.getLaunchServiceProvider().getWikiURL());
                                }

                                intent.putExtras(mBundle);
                                startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    public ArrayList<Launch> retrieveLaunchDataFromResponse(String response) {
        ArrayList<Launch> listOfLaunches = new ArrayList<Launch>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            Log.v(TAG, jsonObject.toString());

            JSONArray launchesJSONArray = jsonObject.getJSONArray("launches");
            Log.v(TAG, launchesJSONArray.toString());


            for (int i = 0; i < launchesJSONArray.length(); i++) {
                JSONObject temp_launch_json_object = launchesJSONArray.getJSONObject(i);
                listOfLaunches.add(new Launch(temp_launch_json_object));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listOfLaunches;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        generalRocketLaunchAdapter.getFilter().filter(s);
        return true;
    }
}
