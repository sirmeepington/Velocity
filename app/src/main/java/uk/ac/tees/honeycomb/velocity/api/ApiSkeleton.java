package uk.ac.tees.honeycomb.velocity.api;

import java.util.ArrayList;

import uk.ac.tees.honeycomb.velocity.stops.BusStop;

public interface ApiSkeleton {

    void busStopTimetable(BusStop id);

    void journey(BusStop idFrom, BusStop idTo);

    void getBusStops(String serviceId, BusStop destination);

    void getServices(BusStop id);

    void getBusTime(String serviceId, BusStop current, BusStop destination);

    ArrayList<BusStop> getService(String serviceId, BusStop destination);
}

