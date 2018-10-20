package spaceappsottawa.launchnow.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mission {

    private int id;
    private String name;
    private String description;
    private String wikiURL;
    private String typeName;

    private ArrayList<String> payloads;

    public Mission(JSONObject missionJSONObject) {
        try {
            if (!missionJSONObject.isNull("id")) {
                setId(missionJSONObject.getInt("id"));
            }
            if (!missionJSONObject.isNull("name")) {
                setName(missionJSONObject.getString("name"));
            }
            if (!missionJSONObject.isNull("description")) {
                setDescription(missionJSONObject.getString("description"));
            }
            if (!missionJSONObject.isNull("wikiURL")) {
                setWikiURL(missionJSONObject.getString("wikiURL"));
            }
            if (!missionJSONObject.isNull("typeName")) {
                setTypeName(missionJSONObject.getString("typeName"));
            }
            if (!missionJSONObject.isNull("payloads")) {
                payloads = new ArrayList<String>();
                JSONArray tempJSONArray = missionJSONObject.getJSONArray("payloads");
                for (int i = 0; i < tempJSONArray.length(); i++) {
                    payloads.add(tempJSONArray.getJSONObject(i).getString("name"));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ArrayList<String> getPayloads() {
        return payloads;
    }
}
