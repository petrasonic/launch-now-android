package spaceappsottawa.launchnow;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailedLaunchActivity extends AppCompatActivity {

    private LinearLayout linear_layout_1, linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second;
    private Handler handler = new Handler();
    private Runnable runnable;

    private String launchName;
    private String launchWindowStart;
    private String launchWindowEnd;
    private int launchStatus;
    private String launchVideoURL;

    private boolean locationExists;
    private String locationName;
    private String locationCountry;
    private String locationPadName;
    private String locationPadMapURL;
    private String locationAgency;

    private boolean rocketExists;
    private String rocketName;
    private String rocketWikiURL;
    private String rocketImageURL;

    private boolean missionExists;
    private String missionName;
    private String missionDescription;
    private String missionWikiURL;
    private String missionTypeName;

    private boolean lspExists;
    private String lspName;
    private String lspWikiURL;

    private TextView launchNameTextView;
    private TextView launchWindowStartTextView;
    private TextView launchWindowEndTextView;
    private TextView launchStatusTextView;
    private TextView launchVideoURLTextView;

    private TextView locationNameTextView;
    private TextView locationCountryTextView;
    private TextView locationPadNameTextView;
    private TextView locationPadMapURLTextView;
    private TextView locationAgencyTextView;

    private TextView rocketNameTextView;
    private TextView rocketWikiURLTextView;
    private TextView rocketImageURLTextView;

    private TextView missionNameTextView;
    private TextView missionDescriptionTextView;
    private TextView missionWikiURLTextView;
    private TextView missionTypeNameTextView;

    private TextView lspNameTextView;
    private TextView lspWikiURLTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_launch);



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

        lspExists = getIntent().getBooleanExtra("lsp_exists", false);
        lspName = getIntent().getStringExtra("lsp_name");
        lspWikiURL = getIntent().getStringExtra("lsp_wiki_url");



        // Initialize the TextView variables.
//        launchNameTextView = (TextView) findViewById(R.id.detailed_launch_name_textView);
//        launchWindowStartTextView = (TextView) findViewById(R.id.detailed_launch_window_start_textView);
//        launchWindowEndTextView = (TextView) findViewById(R.id.detailed_launch_window_end_textView);
        launchStatusTextView = (TextView) findViewById(R.id.detailed_launch_status_textView);
        launchVideoURLTextView = (TextView) findViewById(R.id.detailed_launch_vid_url_textView);

        locationNameTextView = (TextView) findViewById(R.id.detailed_location_name_textView);
//        locationCountryTextView = (TextView) findViewById(R.id.detailed_country_textView);
//        locationPadNameTextView = (TextView) findViewById(R.id.detailed_pad_name_textView);
//        locationPadMapURLTextView = (TextView) findViewById(R.id.detailed_pad_map_url_textView);
        locationAgencyTextView = (TextView) findViewById(R.id.detailed_pad_agency_textView);

        rocketNameTextView = (TextView) findViewById(R.id.detailed_rocket_name_textView);
//        rocketWikiURLTextView = (TextView) findViewById(R.id.detailed_rocket_wiki_url_textView);
//        rocketImageURLTextView = (TextView) findViewById(R.id.detailed_rocket_image_url_textView);

        missionNameTextView = (TextView) findViewById(R.id.detailed_mission_name_textView);
        missionDescriptionTextView = (TextView) findViewById(R.id.detailed_mission_description_textView);
        missionWikiURLTextView = (TextView) findViewById(R.id.detailed_mission_wiki_url_textView);
        missionTypeNameTextView = (TextView) findViewById(R.id.detailed_mission_type_name_textView);

        lspNameTextView = (TextView) findViewById(R.id.detailed_lsp_name_textView);
//        lspWikiURLTextView = (TextView) findViewById(R.id.detailed_lsp_wiki_url_textView);



        // Add text to the text views accordingly.
//        launchNameTextView.setText(launchName);
//        launchWindowStartTextView.setText(launchWindowStart);
//        launchWindowEndTextView.setText(launchWindowEnd);
        launchStatusTextView.setText("" + launchStatus);
        launchVideoURLTextView.setText(launchVideoURL);

        if (locationExists) {
            locationNameTextView.setText(locationPadName);
//            locationCountryTextView.setText(locationCountry);
//            locationPadNameTextView.setText(locationPadName);
//            locationPadMapURLTextView.setText(locationPadMapURL);
            locationAgencyTextView.setText(locationAgency);
        } else {
            locationNameTextView.setText("");
//            locationCountryTextView.setText("");
//            locationPadNameTextView.setText("");
//            locationPadMapURLTextView.setText("");
            locationAgencyTextView.setText("");
        }

        if (rocketExists) {
            rocketNameTextView.setText(rocketName);
//            rocketWikiURLTextView.setText(rocketWikiURL);
//            rocketImageURLTextView.setText(rocketImageURL);
        } else {
            rocketNameTextView.setText("");
//            rocketWikiURLTextView.setText("");
//            rocketImageURLTextView.setText("");
        }

        if (missionExists) {
            missionNameTextView.setText(missionName);
            missionDescriptionTextView.setText(missionDescription);
            missionWikiURLTextView.setText(missionWikiURL);
            missionTypeNameTextView.setText(missionTypeName);
        } else {
            missionNameTextView.setText("");
            missionDescriptionTextView.setText("");
            missionWikiURLTextView.setText("");
            missionTypeNameTextView.setText("");
        }

        if (lspExists) {
            lspNameTextView.setText(lspName);
//            lspWikiURLTextView.setText(lspWikiURL);
        } else {
            lspNameTextView.setText("");
//            lspWikiURLTextView.setText("");
        }

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

}
