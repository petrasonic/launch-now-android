package spaceappsottawa.launchnow.models;

import org.json.JSONException;
import org.json.JSONObject;

public class LaunchServiceProvider {

    private int id = 0;
    private String name = "";
    private String wikiURL = "";

    public LaunchServiceProvider(JSONObject lspJSONObject) {
        try {
            if (!lspJSONObject.isNull("id")) {
                setId(lspJSONObject.getInt("id"));
            }
            if (!lspJSONObject.isNull("name")) {
                setName(lspJSONObject.getString("name"));
            }
            if (!lspJSONObject.isNull("wikiURL")) {
                setWikiURL(lspJSONObject.getString("wikiURL"));
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
}
