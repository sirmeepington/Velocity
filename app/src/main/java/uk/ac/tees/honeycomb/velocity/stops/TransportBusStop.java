package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Locations;

public class TransportBusStop implements BusStop {

    private String atcoCode;

    private String smsCode;

    private String name;

    private Locations location;

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
    public String getATCO() {
        return atcoCode;
    }

    /**
     * Accessor method for the Shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getSmsCode() {
        return smsCode;
    }

    /**
     * Accessor method for the Name of the Bus stop.
     *
     * @return String of the Bus stop name.
     */
    @Override
    public String getName() {
            return name;
    }

    /**
     * Accessor method for the Latitude and Longitude values for the Bus stop.
     *
     * @return Locations class containing Latitude and Longitude.
     */
    @Override
    public Locations getLocation() {
        return location;
    }

    /**
     * Accessor method for the Bearing of the Bus stop.
     *
     * @return String indicating the bearing.
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
}
