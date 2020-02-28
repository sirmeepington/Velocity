package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

/**
 * A response from the {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByCoords}
 * endpoint.
 * @author Aidan
 */
public class StopByCoordsResponse {

    private List<NaptanBusStop> data;
    private String from;
    private double latitude;
    private double longitude;

    /**
     * Returns the list of stops from the response.
     * @return A list of Naptan Bus stops.
     * @see NaptanBusStop
     */
    public List<NaptanBusStop> getData() {
        return data;
    }

    /**
     * Returns the source of the data; this should be NapTAN.
     * @return The data source.
     */
    public String getDataSource() {
        return from;
    }

    /**
     * Gets the latitude of the input coordinates.
     * @return The latitude input.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the input coordinates.
     * @return The longitude input.
     */
    public double getLongitude() {
        return longitude;
    }
}
