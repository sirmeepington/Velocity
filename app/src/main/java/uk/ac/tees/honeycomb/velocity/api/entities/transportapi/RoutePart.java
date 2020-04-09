package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * A step / part of a {@link JourneyRoute}.
 * A route may have many of these parts; these can signify a change
 * in transport or service throughout a route.
 */
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

    /**
     * Returns the estimated time to which you will arrive to this part.
     * @return The arrival time of this part.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns a list of coordinates which create a line on a map.
     * These coordinates are ordered from the beginning of this part to the end of the part
     * and can be used for illustration on a map.
     * @return An ordered array of coordinates.
     */
    public Location[] getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the departure time for this part.
     * @return The departure time.
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the name of the destination for this part for the user to interpret.
     * @return The destination name for this part.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * A string displaying the time in minutes and hours this part of the route will
     * take.
     * @return The route part's duration.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * The name of the origin of this route part.
     * @return The route part's origin name.
     */
    public String getFromName() {
        return from_point_name;
    }

    /**
     * The line or service name for this route. May be null for foot travel.
     * @return The line name.
     */
    public String getLineName() {
        return line_name;
    }

    /**
     * Gets the mode of transport used.
     * @return The mode of transport.
     */
    public String getMode() {
        return mode;
    }

    /**
     * Returns the destination name for this route part.
     * @return The destination name.
     */
    public String getToName() {
        return to_point_name;
    }
}
