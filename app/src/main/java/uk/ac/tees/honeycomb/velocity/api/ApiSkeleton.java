package uk.ac.tees.honeycomb.velocity.api;

import java.util.ArrayList;

import uk.ac.tees.honeycomb.velocity.stops.BusStop;

public interface ApiSkeleton {

    /**
     * Time table checker for a specified Bus stop.
     *
     * @param id ATCO code for the Bus Stop we want to see the time table of.
     */
    void busStopTimetable(BusStop id);

    /**
     * Checks what services would best suit your need to go from point A to point B.
     *
     * @param idFrom Bus Stop ATCO we want to find services from.
     * @param idTo Bus Stop ATCO we want to find services to.
     */
    void journeyPlanner(BusStop idFrom, BusStop idTo);

    /**
     * Checks the bus stops that appear on a Services route from point A to point B.
     *
     * @param serviceId Service ID of the bus we want information from.
     * @param current Starting bound for the route we want to check.
     * @param destination Ending bound for the route we want to check.
     */
    void getBusStops(String serviceId, BusStop current, BusStop destination);

    /**
     * Gets a list of services that stop at a specific Bus stop.
     *
     * @param id ATCO code of the Bus stop we want to check.
     */
    void getServices(BusStop id);

    /**
     * Calculates an estimate time for how long it may take for a Service to make it from point A to point B.
     *
     * @param serviceId Service ID of the bus we want information from.
     * @param current Starting bus stop we want to time check.
     * @param destination Ending bus stop we want to time check.
     */
    void getBusTime(String serviceId, BusStop current, BusStop destination);

    /**
     * Gets a list of BusStop's that a Service passes to its destination.
     *
     * @param serviceId Service ID of the bus we want information from.
     * @param destination End destination of how far we want to check the services route.
     * @return Array list of BusStop's retrieved from the api.
     */
    ArrayList<BusStop> getService(String serviceId, BusStop destination);
}

