package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.BusStop;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

/**
 * A response given by running the endpoint
 * {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName}.
 */
public class StopByNameResponse {

    /**
     * The {@link NaptanBusStop} stops from the NapTaN database.
     */
    private List<NaptanBusStop> data;

    /**
     * A string showing where the data is from.
     */
    private String from;

    /**
     * Returns the stop data list.
     * @return A list of stops that match the name given.
     */
    public List<NaptanBusStop> getStops(){
        return data;
    }

    public String getDataSource(){
        return from;
    }

}
