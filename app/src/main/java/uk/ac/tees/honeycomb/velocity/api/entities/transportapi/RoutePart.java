package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

public class RoutePart {

    private String arrivalTime;
    private Location[] coordinates;
    private String departureTime;
    private String destination;
    private String duration;
    private String from_point_name;
    private String line_name;
    private String mode;
    private String to_point_name;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Location[] getCoordinates() {
        return coordinates;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getDuration() {
        return duration;
    }

    public String getFromName() {
        return from_point_name;
    }

    public String getLineName() {
        return line_name;
    }

    public String getMode() {
        return mode;
    }

    public String getToName() {
        return to_point_name;
    }
}
