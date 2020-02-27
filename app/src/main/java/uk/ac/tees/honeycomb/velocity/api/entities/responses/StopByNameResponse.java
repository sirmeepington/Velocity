package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.BusStop;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

/**
 * A response given by running the endpoint
 * {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByName}.
 * @author Aidan
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
     * The name specified via the input.
     */
    private String name;

    /**
     * Returns the stop data list.
     * @return A list of stops that match the name given.
     */
    public List<NaptanBusStop> getStops(){
        return data;
    }

    /**
     * Returns the name of the source for this data. This should be NapTAN for bus stop data as
     * it is updated regularly and is cached on the back-end system.
     * @return The name of source for the data.
     */
    public String getDataSource(){
        return from;
    }

    /**
     * Returns the name that was input for this set of data to be returned.
     * @return The name that was inputted.
     */
    public String getName(){
        return name;
    }

}
