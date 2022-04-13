import metro.Line;
import metro.Station;
import metro.Stations;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static Document document;
    private static List<Line> lines;
    private static Stations stations;

    public Parser(String url) throws Exception {
        document = Jsoup.connect(url).maxBodySize(0).get();
        lines = new ArrayList<>();
        stations = new Stations();
    }

    public List<Line> getLines() {
        return lines;
    }

    public Stations getContainerStations() {
        return stations;
    }

    public JSONArray parseLine() {
        Elements linesList = document.getElementsByAttributeValueStarting("class", "js-metro-line t-metrostation-list-header t-icon-metroln ln");
        JSONArray linesObjectArray = new JSONArray();
        for (Element element : linesList) {
            Line line = new Line(element.attr("data-line"), element.text());
            linesObjectArray.add(line.getJsonLine());
            lines.add(line);
        }
        return linesObjectArray;
    }

    public JSONObject parseStation() {
        Elements dataList = document.getElementsByClass("js-metro-stations t-metrostation-list-table");
        JSONObject stationsObject = new JSONObject();
        for (Element element : dataList) {
            JSONArray stationsArray = new JSONArray();
            Elements stationsList = element.getElementsByClass("name");
            for (Element stationElement : stationsList) {
                stationsArray.add(stationElement.text());
                stationsObject.put(element.attr("data-line"), stationsArray);
                stations.addStation(new Station(stationElement.text(), element.attr("data-line")));
            }
        }
        return stationsObject;
    }
}