package spaceappsottawa.launchnow.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Location {

    private int id;
    private String name;
    private String countryCode;

    private String padName;
    private String padMapURL;
    private String padAgencyName;


    public Location(JSONObject locationJSONObject) {
        try {
            if (!locationJSONObject.isNull("id")) {
                setId(locationJSONObject.getInt("id"));
            }
            if (!locationJSONObject.isNull("name")) {
                setName(locationJSONObject.getString("name"));
            }
            if (!locationJSONObject.isNull("countryCode")) {
                setCountryCode(locationJSONObject.getString("countryCode"));
            }
            if (!locationJSONObject.isNull("pads")) {
                if (locationJSONObject.getJSONArray("pads").length() != 0) {
                    setPadName(locationJSONObject.getJSONArray("pads").getJSONObject(0).getString("name"));
                    setPadMapURL(locationJSONObject.getJSONArray("pads").getJSONObject(0).getString("mapURL"));
                    if (!locationJSONObject.getJSONArray("pads").getJSONObject(0).isNull("agencies")) {
                        if (locationJSONObject.getJSONArray("pads").getJSONObject(0).getJSONArray("agencies").length() != 0) {
                            setPadAgencyName(locationJSONObject.getJSONArray("pads").getJSONObject(0).getJSONArray("agencies").getJSONObject(0).getString("name"));
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPadName() {
        return padName;
    }

    public void setPadName(String padName) {
        this.padName = padName;
    }

    public String getPadMapURL() {
        return padMapURL;
    }

    public void setPadMapURL(String padMapURL) {
        this.padMapURL = padMapURL;
    }

    public String getPadAgencyName() {
        return padAgencyName;
    }

    public void setPadAgencyName(String padAgencyName) {
        this.padAgencyName = padAgencyName;
    }
}
