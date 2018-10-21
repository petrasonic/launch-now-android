package spaceappsottawa.launchnow.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Launch {

    private int id;
    private String name = "";
    private String windowStart = "";
    private String windowEnd = "";
    private String net = "";
    private int status;

    private String vidURLs = "";

    private Location location;

    private Rocket rocket;

    private Mission mission;

    private LaunchServiceProvider launchServiceProvider;

    public Launch(JSONObject launchJSONObject) {
        try {
            if (!launchJSONObject.isNull("id")) {
                setId(launchJSONObject.getInt("id"));
            }
            if (!launchJSONObject.isNull("name")) {
                setName(launchJSONObject.getString("name"));
            }
            if (!launchJSONObject.isNull("windowstart")) {
                setWindowStart(launchJSONObject.getString("windowstart"));
            }
            if (!launchJSONObject.isNull("windowend")) {
                setWindowEnd(launchJSONObject.getString("windowend"));
            }
            if (!launchJSONObject.isNull("net")) {
                setNet(launchJSONObject.getString("net"));
            }
            if (!launchJSONObject.isNull("status")) {
                setStatus(launchJSONObject.getInt("status"));
            }
            if (!launchJSONObject.isNull("vidURLs")) {
                if (launchJSONObject.getJSONArray("vidURLs").length() != 0) {
                    setVidURLs(launchJSONObject.getJSONArray("vidURLs").get(0).toString());
                }
            }
            if (!launchJSONObject.isNull("location")) {
                setLocation(new Location(launchJSONObject.getJSONObject("location")));
            }
            if (!launchJSONObject.isNull("rocket")) {
                setRocket(new Rocket(launchJSONObject.getJSONObject("rocket")));
            }
            if (!launchJSONObject.isNull("missions")) {
                if (launchJSONObject.getJSONArray("missions").length() != 0) {
                    setMission(new Mission(launchJSONObject.getJSONArray("missions").getJSONObject(0)));
                }
            }
            if (!launchJSONObject.isNull("lsp")) {
                setLaunchServiceProvider(new LaunchServiceProvider(launchJSONObject.getJSONObject("lsp")));
            }

        } catch (JSONException ignored) {
            Log.v("LAUNCH", ignored.toString());

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

    public String getWindowStart() {
        return windowStart;
    }

    public void setWindowStart(String windowStart) {
        this.windowStart = windowStart;
    }

    public String getWindowEnd() {
        return windowEnd;
    }

    public void setWindowEnd(String windowEnd) {
        this.windowEnd = windowEnd;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVidURLs() {
        return vidURLs;
    }

    public void setVidURLs(String vidURLs) {
        this.vidURLs = vidURLs;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public LaunchServiceProvider getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(LaunchServiceProvider launchServiceProvider) {
        this.launchServiceProvider = launchServiceProvider;
    }
}
