package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Bearing;
import uk.ac.tees.honeycomb.velocity.entities.Location;

/**
 * Class to interact with the TransportApi api to request and retrieve data.
 * Implements the Bus Stop interface.
 */
public class TransportBusStop implements BusStop {

    private String atcoCode;

    private String smsCode;

    private String name;

    private String stopName;

    private Location location;

    private Bearing bearing;

    private String indicator;

    private String locality;

    public TransportBusStop() {

    }

    /**
     * Accessor method for the Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getAtcoCode() {
        return atcoCode;
    }

    /**
     * Accessor method for the shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * Accessor method for the name of the area.
     *
     * @return String of the Bus stop area.
     */
    @Override
    public String getName() {
            return name;
    }

    /**
     * Accessor method for the Latitude and Longitude values for the Bus stop.
     *
     * @return Location class containing Latitude and Longitude.
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Accessor method for the Bearing of the Bus stop.
     *
     * @return Bearing enum indicating the bearing of the bus stop.
     */
    @Override
    public Bearing getBearing() {
        return bearing;
    }

    /**
     * Accessor method for the Indicator for which direction the Bus is travelling.
     *
     * @return String of the indicator.
     */
    @Override
    public String getIndicator() {
        return indicator;
    }

    /**
     * Accessor method for the Locality/Location of the Bus stop.
     *
     * @return General area and Town name of the Bus stop.
     */
    @Override
    public String getLocality() {
        return locality;
    }

    /**
     * Accessor method for the name of the Bus stop.
     *
     * @return Name of the Bus stop.
     */
    public String getStopName() { return stopName; }
}
