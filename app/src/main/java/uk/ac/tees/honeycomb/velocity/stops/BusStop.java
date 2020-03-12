package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Bearing;
import uk.ac.tees.honeycomb.velocity.entities.Location;

public interface BusStop {

    /**
     * Accessor method for the Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    String getAtcoCode();

    /**
     * Accessor method for the shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    String getSmsCode();

    /**
     * Accessor method for the name of the area.
     *
     * @return String of the Bus stop area.
     */
    String getName();

    /**
     * Accessor method for the Latitude and Longitude values for the Bus stop.
     *
     * @return Location class containing Latitude and Longitude.
     */
    Location getLocation();

    /**
     * Accessor method for the Bearing of the Bus stop.
     *
     * @return Bearing enum indicating the bearing of the bus stop.
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
