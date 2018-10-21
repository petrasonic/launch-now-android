package spaceappsottawa.launchnow;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DetailedLaunchActivity extends AppCompatActivity {

    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;

    private String launchName = "";
    private String launchWindowStart = "";
    private String launchWindowEnd = "";
    private int launchStatus;
    private String launchVideoURL = "";

    private boolean locationExists = false;
    private String locationName = "";
    private String locationCountry = "";
    private String locationPadName = "";
    private String locationPadMapURL = "";
    private String locationAgency = "";

    private boolean rocketExists = false;
    private String rocketName = "";
    private String rocketWikiURL = "";
    private String rocketImageURL = "";

    private boolean missionExists = false;
    private String missionName = "";
    private String missionDescription = "";
    private String missionWikiURL = "";
    private String missionTypeName = "";
    private ArrayList<String> missionPayloads = new ArrayList<String>();

    private boolean lspExists = false;
    private String lspName = "";
    private String lspWikiURL = "";

    private TextView launchStatusTextView;
    private TextView launchVideoURLTextView;

    private TextView locationNameTextView;
    private TextView locationAgencyTextView;

    private TextView rocketNameTextView;

    private TextView missionNameTextView;
    private TextView missionDescriptionTextView;
    private TextView missionWikiURLTextView;
    private TextView missionTypeNameTextView;
    private TextView missionPayloadsTextView;

    private TextView lspNameTextView;

    private ImageButton mapImageButton;
    private LinearLayout missionLinearLayout;
    private LinearLayout payloadsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_launch);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        // Retrieve data from the bundle:
        launchName = getIntent().getStringExtra("launch_name");
        launchWindowStart = getIntent().getStringExtra("launch_window_start");
        launchWindowEnd = getIntent().getStringExtra("launch_window_end");
        launchStatus = getIntent().getIntExtra("launch_status", 0);
        launchVideoURL = getIntent().getStringExtra("launch_video_url");

        locationExists = getIntent().getBooleanExtra("location_exists", false);
        locationName = getIntent().getStringExtra("location_name");
        locationCountry = getIntent().getStringExtra("location_country");
        locationPadName = getIntent().getStringExtra("location_pad_name");
        locationPadMapURL = getIntent().getStringExtra("location_pad_map_url");
        locationAgency = getIntent().getStringExtra("location_agency");

        rocketExists = getIntent().getBooleanExtra("rocket_exists", false);
        rocketName = getIntent().getStringExtra("rocket_name");
        rocketWikiURL = getIntent().getStringExtra("rocket_wiki_url");
        rocketImageURL = getIntent().getStringExtra("rocket_image_url");

        missionExists = getIntent().getBooleanExtra("mission_exists", false);
        missionName = getIntent().getStringExtra("mission_name");
        missionDescription = getIntent().getStringExtra("mission_description");
        missionWikiURL = getIntent().getStringExtra("mission_wiki_url");
        missionTypeName = getIntent().getStringExtra("mission_type_name");
        missionPayloads = getIntent().getStringArrayListExtra("mission_payloads");

        lspExists = getIntent().getBooleanExtra("lsp_exists", false);
        lspName = getIntent().getStringExtra("lsp_name");
        lspWikiURL = getIntent().getStringExtra("lsp_wiki_url");


        // Initialize the TextView variables.
        launchStatusTextView = (TextView) findViewById(R.id.detailed_launch_status_textView);
        launchVideoURLTextView = (TextView) findViewById(R.id.detailed_launch_vid_url_textView);

        locationNameTextView = (TextView) findViewById(R.id.detailed_location_name_textView);
        locationAgencyTextView = (TextView) findViewById(R.id.detailed_pad_agency_textView);

        rocketNameTextView = (TextView) findViewById(R.id.detailed_rocket_name_textView);

        missionNameTextView = (TextView) findViewById(R.id.detailed_mission_name_textView);
        missionDescriptionTextView = (TextView) findViewById(R.id.detailed_mission_description_textView);
        missionWikiURLTextView = (TextView) findViewById(R.id.detailed_mission_wiki_url_textView);
        missionTypeNameTextView = (TextView) findViewById(R.id.detailed_mission_type_name_textView);
        missionPayloadsTextView = (TextView) findViewById(R.id.detailed_mission_payloads_textView);

        lspNameTextView = (TextView) findViewById(R.id.detailed_lsp_name_textView);
        missionLinearLayout = (LinearLayout) findViewById(R.id.mission_linear_layout);
        payloadsLinearLayout = (LinearLayout) findViewById(R.id.payloads_linear_layout);

        mapImageButton = (ImageButton) findViewById(R.id.map_image_button);
        mapImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, item.getMap_url(), Toast.LENGTH_SHORT).show();
                String map_url = locationPadMapURL.replace("MAP URL: ", "");
                if (!map_url.equals("")) {
                    // Create a Uri from an intent string. Use the result to create an Intent.
                    Uri gmmIntentUri = Uri.parse(map_url);

                    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                    // Attempt to start an activity that can handle the Intent
                    // Toast.makeText(MainActivity.this, item.getMap_url(), Toast.LENGTH_SHORT).show();
                    startActivity(mapIntent);

                } else {
                    Toast.makeText(DetailedLaunchActivity.this, "This launch does not have a Map URL!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Add text to the text views accordingly.
        switch (launchStatus) {
            case 1:
                launchStatusTextView.setText("On Schedule");
                launchStatusTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case 2:
                launchStatusTextView.setText("Possible Delay");
                launchStatusTextView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                break;
            case 3:
                launchStatusTextView.setText("On Schedule");
                launchStatusTextView.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case 4:
                launchStatusTextView.setText("Possible Delay");
                launchStatusTextView.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                break;
        }

        if (launchVideoURL.equals("")) {
            launchVideoURLTextView.setText((""));
        } else {
            launchVideoURLTextView.setMovementMethod(LinkMovementMethod.getInstance());
            String temp_vid_url = "<a href=\"" + launchVideoURL + "\">Live Stream ▶</a>️";
            launchVideoURLTextView.setText((Html.fromHtml(temp_vid_url)));
        }

        if (locationExists) {
            locationNameTextView.setText(locationPadName);
            locationAgencyTextView.setText(locationAgency);
        } else {
            locationNameTextView.setText("");
            locationAgencyTextView.setText("");
        }

        if (rocketExists) {
            if (rocketWikiURL.equals("")) {
                rocketNameTextView.setText(rocketName);
            } else {
                rocketNameTextView.setMovementMethod(LinkMovementMethod.getInstance());
                String temp_rocket_url = "<a href=\"" + rocketWikiURL + "\">" + rocketName + "</a>️";
                rocketNameTextView.setText((Html.fromHtml(temp_rocket_url)));
            }
        } else {
            rocketNameTextView.setText("");
        }

        if (missionExists) {
            missionLinearLayout.setVisibility(View.VISIBLE);
            missionNameTextView.setText(missionName);
            missionDescriptionTextView.setText(missionDescription);
            missionWikiURLTextView.setText(missionWikiURL);
            if (!missionWikiURL.equals("")) {
                missionWikiURLTextView.setMovementMethod(LinkMovementMethod.getInstance());
                String temp_mission_url = "<a href=\"" + missionWikiURL + "\">" + "Read More" + "</a>️";
                missionWikiURLTextView.setText((Html.fromHtml(temp_mission_url)));
            }
            missionTypeNameTextView.setText(missionTypeName);
            String temp_mission_payloads = "";
            for (int i = 0; i < missionPayloads.size(); i++) {
                temp_mission_payloads += missionPayloads.get(i) + "\n";
            }
            if (!temp_mission_payloads.equals("")) {
                payloadsLinearLayout.setVisibility(View.VISIBLE);
                missionPayloadsTextView.setText(temp_mission_payloads);
            } else {
                payloadsLinearLayout.setVisibility(View.GONE);
            }
        } else {
            missionLinearLayout.setVisibility(View.GONE);
            missionNameTextView.setText("");
            missionDescriptionTextView.setText("");
            missionWikiURLTextView.setText("");
            missionTypeNameTextView.setText("");
            missionPayloadsTextView.setText("");
        }

        if (lspExists) {
            if (lspWikiURL.equals("")) {
                lspNameTextView.setText(lspName);
            } else {
                lspNameTextView.setMovementMethod(LinkMovementMethod.getInstance());
                String temp_lsp_url = "<a href=\"" + lspWikiURL + "\">" + lspName + "</a>️";
                lspNameTextView.setText((Html.fromHtml(temp_lsp_url)));
            }
        } else {
            lspNameTextView.setText("");
        }

        setTitle(launchName);

        initUI();
        countDownStart();

    }

    private void initUI() {
        linear_layout_1 = findViewById(R.id.linear_layout_1);
        linear_layout_2 = findViewById(R.id.linear_layout_2);
        tv_days = findViewById(R.id.tv_days);
        tv_hour = findViewById(R.id.tv_hour);
        tv_minute = findViewById(R.id.tv_minute);
        tv_second = findViewById(R.id.tv_second);
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss zzz", Locale.US);
                    Date event_date = sdf.parse(launchWindowStart);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        //
                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
                    } else {
                        linear_layout_1.setVisibility(View.VISIBLE);
                        linear_layout_2.setVisibility(View.GONE);
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_remind_me:
                Toast.makeText(DetailedLaunchActivity.this, "Feature Coming Soon!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_explore_twitter_feed: {
                Toast.makeText(DetailedLaunchActivity.this, "Feature Coming Soon!", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action_see_additional_weather_details: {
                Toast.makeText(DetailedLaunchActivity.this, "Feature Coming Soon!", Toast.LENGTH_SHORT).show();
                break;
            }

        }
        return true;
    }

}
