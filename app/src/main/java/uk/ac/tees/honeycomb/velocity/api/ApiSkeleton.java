package uk.ac.tees.honeycomb.velocity.api;

import java.util.ArrayList;

public interface ApiSkeleton {

    void busStopTimetable(String id);

    void journey(String idFrom, String idTo);

    void getBusStops(String serviceId, String destination);

    void getServices(String id);

    void getBusTime(String serviceId, String current, String destination);

    ArrayList<String> getService(String serviceId, String destination);

    void getLocation();
}

