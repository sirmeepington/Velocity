package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

/**
 * A response to the {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopByPostcode}
 * endpoint request.
 * The data from this endpoint should be {@code NapTAN} as it is the cached data.
 */
public class StopByPostcodeResponse {

    private List<NaptanBusStop> data;
    private List<String> from;
    private double latitude;
    private double longitude;
    private String postcode;

    /**
     * Retrieves the list of bus stops returned from the Impetus endpoint.
     * @return A list of bus stops.
     */
    public List<NaptanBusStop> getData() {
        return data;
    }

    /**
     * Returns a list stating the data sources for this endpoint. This should be NapTAN and
     * postcodes.io.
     * @return Returns the data sources.
     */
    public List<String> getSources() {
        return from;
    }

    /**
     * Returns the parsed latitude given by postcodes.io.
     * @return The latitude of the postcode given.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the parsed longitude given by postcodes.io.
     * @return The longitude of the postcode given.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The postcode that was given via input.
     * @return The postcode given.
     */
    public String getPostcode() {
        return postcode;
    }
}
