package spaceappsottawa.launchnow.models;

import org.json.JSONException;
import org.json.JSONObject;

public class RocketLaunchListViewItem {

    public static final String TAG = "RocketLaunch";

    private String name; // The name of the rocket
    private String company; // The company that is launching the rocket
    private String location; // The location of the rocket launch
    private String date; // The date of the rocket launch

    public RocketLaunchListViewItem(JSONObject rocketLaunchJSONObject) {
        try {
            if (!rocketLaunchJSONObject.isNull("name")) {
                setName(rocketLaunchJSONObject.getString("name"));
            }

            if (!rocketLaunchJSONObject.isNull("company")) {
                setCompany(rocketLaunchJSONObject.getString("company"));
            }

            if (!rocketLaunchJSONObject.isNull("location")) {
                setLocation(rocketLaunchJSONObject.getString("location"));
            }

            if (!rocketLaunchJSONObject.isNull("date")) {
                setDate(rocketLaunchJSONObject.getString("date"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
