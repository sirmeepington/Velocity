package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Locations;

public interface BusStop {
    /**
     * Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    String getATCO();

    /**
     * Shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    String getSmsCode();

    /**
     * Name of the Bus stop.
     *
     * @return String of the Bus stop name.
     */
    String getName();

    /**
     * Latitude and Longitude values for the Bus stop.
     *
     * @return Locations class containing Latitude and Longitude.
     */
    Locations getLocation();

    /**
     * Bearing of the Bus stop.
     *
     * @return String indicating the bearing.
     */
    String bearing();

    /**
     * Indicator for which direction the Bus is travelling.
     *
     * @return String of the indicator.
     */
    String indicator();

    /**
     * Locality/Location of the Bus stop.
     *
     * @return General area and Town name of the Bus stop.
     */
    String locality();
}
