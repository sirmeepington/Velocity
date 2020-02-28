package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

/**
 * The response from the
 * {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopFromAtcoCode} endpoint.
 * @see uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopFromAtcoCode
 * @author Aidan
 */
public class StopFromAtcoCodeResponse {

    private String code;
    private List<NaptanBusStop> data;
    private String from;

    /**
     * Returns the ATCO code given by the request.
     * @return The request's ATCO code.
     */
    public String getCode() {
        return code;
    }

    /**
     * The NapTAN bus stop returned from the query. Can be null if no stop was found.
     * @return The stop whose ATCO code matches the one given.
     */
    public NaptanBusStop getData() {
        return data == null ? null : data.get(0);
    }

    /**
     * Returns where the data is from.
     * @return The data source; this should be NapTAN.
     */
    public String getDataSource() {
        return from;
    }
}
