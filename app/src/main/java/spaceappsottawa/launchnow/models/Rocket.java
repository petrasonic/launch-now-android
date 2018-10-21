package spaceappsottawa.launchnow.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Rocket {

    private int id;
    private String name = "";
    private String wikiURL = "";
    private String imageURL = "";

    public Rocket(JSONObject rocketJSONObject) {
        try {
            if (!rocketJSONObject.isNull("id")) {
                setId(rocketJSONObject.getInt("id"));
            }
            if (!rocketJSONObject.isNull("name")) {
                setName(rocketJSONObject.getString("name"));
            }
            if (!rocketJSONObject.isNull("wikiURL")) {
                setWikiURL(rocketJSONObject.getString("wikiURL"));
            }
            if (!rocketJSONObject.isNull("imageURL")) {
                setImageURL(rocketJSONObject.getString("imageURL"));
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

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
