import core.Line;
import core.Station;
import junit.framework.TestCase;


import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest  extends TestCase {
    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;

    @Override
    protected void setUp() {
       route = new ArrayList<>();
        Line line5 = new Line(5, "purple");
        Line line2 = new Line(2, "blue");
        Line line3 = new Line(3, "green");

        route.add(new Station("5.1", line5));
        route.add(new Station("5.2", line5));
        route.add(new Station("2.1", line2));
        route.add(new Station("2.2", line2));
        route.add(new Station("2.3", line2));
        route.add(new Station("3.1", line3));
        route.add(new Station("3.2", line3));
        route.add(new Station("3.3", line3));

        line2.addStation(route.get(2));
        line2.addStation(route.get(3));
        line2.addStation(route.get(4));
        line3.addStation(route.get(5));
        line3.addStation(route.get(6));
        line3.addStation(route.get(7));
        line5.addStation(route.get(0));
        line5.addStation(route.get(1));

        List<Station> connectionStations1 = new ArrayList<>();
            connectionStations1.add(route.get(1));
            connectionStations1.add(route.get(3));
        List<Station> connectionStations2 = new ArrayList<>();
            connectionStations2.add(route.get(4));
            connectionStations2.add(route.get(6));



        stationIndex = new StationIndex();
        route.forEach(s -> stationIndex.addStation(s));
        route.stream().map(Station::getLine).toList().stream().distinct().forEach(l -> stationIndex.addLine(l));

        route.forEach(station -> stationIndex.addLine(station.getLine()));
        stationIndex.addConnection(connectionStations1);
        stationIndex.addConnection(connectionStations2);
        routeCalculator = new RouteCalculator(stationIndex);
    }


    public void testCalculateDuration (){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 19.5;
        assertEquals(expected,actual);
    }

    public void testGetShortestRouteTwoConnections () {
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        expected.add(route.get(3));
        expected.add(route.get(4));
        expected.add(route.get(6));
        expected.add(route.get(7));


        Station from = route.get(0);
        Station to = route.get(7);
        List <Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteOneConnections () {
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));
        expected.add(route.get(3));
        expected.add(route.get(2));


        Station from = route.get(0);
        Station to = route.get(2);
        List <Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }

    public void testGetShortestRouteNoConnections () {
        List<Station> expected = new ArrayList<>();
        expected.add(route.get(0));
        expected.add(route.get(1));


        Station from = route.get(0);
        Station to = route.get(1);
        List <Station> actual = routeCalculator.getShortestRoute(from, to);
        assertEquals(expected, actual);
    }



    @Override
    protected void tearDown() {
    }
}
