package spaceappsottawa.launchnow.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RocketLaunchListViewItem {

    public static final String TAG = "RocketLaunch";

    private String name = ""; // The name of the rocket
    private String company = ""; // The company that is launching the rocket
    private String location = ""; // The location of the rocket launch
    private String date = ""; // The date of the rocket launch



    public RocketLaunchListViewItem(JSONObject rocketLaunchJSONObject) {
        try {
            if (!rocketLaunchJSONObject.isNull("name")) {
                setName(rocketLaunchJSONObject.getString("name"));
            }

            if (!rocketLaunchJSONObject.isNull("company")) {
                setCompany(rocketLaunchJSONObject.getString("company"));
            }

            if (!rocketLaunchJSONObject.isNull("location")) {
                JSONObject locationJSONObject = rocketLaunchJSONObject.getJSONObject("location");
                JSONArray padsJSONArray = locationJSONObject.getJSONArray("pads");

                StringBuilder final_location = new StringBuilder();

                String temp_location_country_code = "COUNTRY CODE: " + locationJSONObject.getString("countryCode");

                final_location.append(temp_location_country_code).append('\n');;

                for (int i = 0; i < padsJSONArray.length(); i++) {
                    String temp_pad_name = "PAD NAME: ";
                    String temp_map_url = "MAP URL: ";
                    temp_pad_name += padsJSONArray.getJSONObject(i).getString("name");
                    temp_map_url += padsJSONArray.getJSONObject(i).getString("mapURL");

                    final_location.append(temp_pad_name).append('\n');
                    final_location.append(temp_map_url).append('\n');
                    JSONArray padAgencies = padsJSONArray.getJSONObject(i).getJSONArray("agencies");
                    for (int j = 0; j < padAgencies.length(); j++) {
                        String temp_agency_name = "AGENCY NAME: ";
                        temp_agency_name += padAgencies.getJSONObject(i).getString("name");
                        final_location.append(temp_agency_name).append('\n');;
                    }
                }

                setLocation(final_location.toString());

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
