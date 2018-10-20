package spaceappsottawa.launchnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
    private TextView tempJsonTextView;

    private BaseAdapter generalRocketLaunchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generalLaunchDataListView = (ListView) findViewById(R.id.general_launch_data_listview);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "ICON (Ionospheric Connection Explorer)");
            jsonObject.put("company", "NASA");
            jsonObject.put("location", "Kodiak Island");
            jsonObject.put("date", "October 26, 2018");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RocketLaunchListViewItem rocketLaunchListViewItem = new RocketLaunchListViewItem(jsonObject);

        ArrayList<RocketLaunchListViewItem> listOfRocketLaunches = new ArrayList<>();
        listOfRocketLaunches.add(rocketLaunchListViewItem);

        generalRocketLaunchAdapter = new GeneralRocketLaunchDataListViewAdapter(MainActivity.this, listOfRocketLaunches);
        generalLaunchDataListView = (ListView) findViewById(R.id.general_launch_data_listview);
        generalLaunchDataListView.setAdapter(generalRocketLaunchAdapter);

        // makeTempJsonRequest();
    }

    public void makeTempJsonRequest() {
        // Instantiate the RequestQueue.
        String requestURL = BASE_URL + "/api/example";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tempJsonTextView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, error.toString());
                tempJsonTextView.setText("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }

}
