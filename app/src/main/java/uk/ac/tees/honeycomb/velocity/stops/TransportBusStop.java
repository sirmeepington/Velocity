package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Locations;

public class TransportBusStop implements BusStop {

    public TransportBusStop() {

    }

    /**
     * Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getATCO() {
        return null;
    }

    /**
     * Shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getSmsCode() {
        return null;
    }

    /**
     * Name of the Bus stop.
     *
     * @return String of the Bus stop name.
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Latitude and Longitude values for the Bus stop.
     *
     * @return Locations class containing Latitude and Longitude.
     */
    @Override
    public Locations getLocation() {
        return null;
    }

    /**
     * Bearing of the Bus stop.
     *
     * @return String indicating the bearing.
     */
    @Override
    public String bearing() {
        return null;
    }

    /**
     * Indicator for which direction the Bus is travelling.
     *
     * @return String of the indicator.
     */
    @Override
    public String indicator() {
        return null;
    }

    /**
     * Locality/Location of the Bus stop.
     *
     * @return General area and Town name of the Bus stop.
     */
    @Override
    public String locality() {
        return null;
    }
}
