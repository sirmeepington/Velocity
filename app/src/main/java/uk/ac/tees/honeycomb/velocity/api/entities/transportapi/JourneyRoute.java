package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

public class JourneyRoute {

    private String arrivalTime;
    private String departureTime;
    private String duration;
    private RoutePart[] routeParts;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public RoutePart[] getRouteParts() {
        return routeParts;
    }
}
