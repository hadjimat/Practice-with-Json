package metro;

import java.util.*;

public class Stations {

    private final List<Station> stations;


    public Stations() {
        stations = new ArrayList<>();
    }

    public void addStation(Station station) {
        stations.add(station);
    }


}