import metro.Metro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        Parser parser = new Parser("https://skillbox-java.github.io/");

        JSONArray linesArray = parser.parseLine();

        JSONObject stationsObject = parser.parseStation();

        Metro metro = new Metro(stationsObject, linesArray);

        JSONWriter jsonWriter = new JSONWriter();
        jsonWriter.writeInJSONFile(metro.getMetroObject(), "files/map.json");

        JSONReader jsonReader = new JSONReader();
        jsonReader.getNumberOfStations("files/map.json");
    }
}