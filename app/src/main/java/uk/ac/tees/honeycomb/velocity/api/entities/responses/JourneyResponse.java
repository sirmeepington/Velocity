package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Journey;

public class JourneyResponse {

    private Journey journey;
    private String from;
    private String to;

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
