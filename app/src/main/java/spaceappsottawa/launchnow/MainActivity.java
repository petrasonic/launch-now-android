package spaceappsottawa.launchnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private final static String BASE_URL = "http://10.0.2.2:3001";

    private RequestQueue requestQueue;

    private TextView tempJsonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempJsonTextView = (TextView) findViewById(R.id.temp_json);
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        makeTempJsonRequest();
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
