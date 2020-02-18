package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Locations;

public interface BusStop {

    /**
     * Accessor method for the Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    String getATCO();

    /**
     * Accessor method for the Shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    String getSmsCode();

    /**
     * Accessor method for the Name of the Bus stop.
     *
     * @return String of the Bus stop name.
     */
    String getName();

    /**
     * Accessor method for the Latitude and Longitude values for the Bus stop.
     *
     * @return Locations class containing Latitude and Longitude.
     */
    Locations getLocation();

    /**
     * Accessor method for the Bearing of the Bus stop.
     *
     * @return String indicating the bearing.
     */
    Bearing getBearing();

    /**
     * Accessor method for the Indicator for which direction the Bus is travelling.
     *
     * @return String of the indicator.
     */
    String getIndicator();

    /**
     * Accessor method for the Locality/Location of the Bus stop.
     *
     * @return General area and Town name of the Bus stop.
     */
    String getLocality();

}
