package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Journey;

/**
 * A response class from the Journey Planner endpoints.
 * Contains information such as the given coordinates {@link #from} the beginning location
 * and {@link #to} the destination location.
 *
 * @see uk.ac.tees.honeycomb.velocity.api.entities.endpoints.JourneyFromStops
 * @see uk.ac.tees.honeycomb.velocity.api.entities.endpoints.JourneyFromCoords
 */
public class JourneyResponse {

    private Journey journey;
    private String from;
    private String to;

    /**
     * Gets the {@link Journey} information from the response.
     * @return The {@link Journey} returned.
     */
    public Journey getJourney() {
        return journey;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
