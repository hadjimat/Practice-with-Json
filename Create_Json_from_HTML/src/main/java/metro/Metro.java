package metro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Metro {

    private final JSONObject metroObject;

    public Metro(JSONObject stationsObject, JSONArray linesArray) {
        metroObject = new JSONObject();
        metroObject.put("stations", stationsObject);
        metroObject.put("lines", linesArray);
    }

    public JSONObject getMetroObject() {
        return metroObject;
    }
}