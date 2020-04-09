package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * An available route within a {@link Journey}.
 * Consists of many {@link RoutePart}s.
 *
 * @see RoutePart
 *
 * @author Aidan
 * @since 08/04/20
 */
public class JourneyRoute {

    private String arrivalTime;
    private String departureTime;
    private String duration;
    private RoutePart[] routeParts;

    /**
     * Returns the estimated time of arrival to the destination using
     * this route.
     * @return The estimated time of arrival.
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the estimated departure time from this route.
     * @return The estimated departure time.
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns an estimation on how long this route will take.
     * @return The duration of this route.
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns an array of all the {@link RoutePart}s of this
     * route/
     * @return The array if {@link RoutePart}s.
     */
    public RoutePart[] getRouteParts() {
        return routeParts;
    }
}
