package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

import java.util.ArrayList;

/**
 * Due to TransportAPI's return; this class is required to store the {@link Departure}s.
 */
public class DepartureContainer {

    private ArrayList<Departure> all;

    /**
     * Returns the list of departures.
     * @return The list of departures.
     */
    public ArrayList<Departure> getDepartures() {
        return all;
    }
}
