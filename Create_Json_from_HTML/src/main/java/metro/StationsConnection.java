package metro;

import java.util.TreeSet;

public class StationsConnection implements Comparable<StationsConnection> {

    private final TreeSet<Station> stationsCon;

    public StationsConnection() {
        stationsCon = new TreeSet<>();
    }

    public void addStation(Station station) {
        stationsCon.add(station);
    }

    public TreeSet<Station> getStationsCon() {
        return stationsCon;
    }

    @Override
    public int compareTo(StationsConnection stationsConnection) {
        if(stationsCon.size() == stationsConnection.getStationsCon().size()) {
            if(stationsCon.containsAll(stationsConnection.getStationsCon())) {
                return 0;
            } else {
                return -1;
            }
        }
        if(stationsCon.size() < stationsConnection.getStationsCon().size()) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((StationsConnection) obj) == 0;
    }
}