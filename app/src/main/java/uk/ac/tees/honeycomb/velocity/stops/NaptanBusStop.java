package uk.ac.tees.honeycomb.velocity.stops;

import uk.ac.tees.honeycomb.velocity.entities.Bearing;
import uk.ac.tees.honeycomb.velocity.entities.Locations;

/**
 * Class to interact with the NaPTAN api to request and retrieve data.
 * Implements the Bus Stop interface.
 */
public class NaptanBusStop implements BusStop {

    private String atcoCode;

    private String smsCode;

    private String name;

    private Locations location;

    private String street;

    private String indicator;

    private Bearing bearing;

    private String nptgLocalityCode; /* Matches Pattern [EN][0S][0-9]{6} */

    private String locality;

    private String stopType;

    private String busStopType;

    private String timingStatus;

    public NaptanBusStop() {

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
     * Accessor method for the name of the area
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
     * @return Locations class containing Latitude and Longitude.
     */
    @Override
    public Locations getLocation() {
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
     * Accessor method for the Street of the Bus stop.
     *
     * @return The street name the Bus stop is located on.
     */
    public String getStreet() { return street;}

    /**
     * Accessor method or the Locality code of the Bus stop locality.
     *
     * @return
     */
    public String getNptgLocalityCode() {
        return nptgLocalityCode;
    }

    /**
     * Accessor method for the stop type provided by the API.
     * We are only interested in stops labelled "BCT".
     *
     * @return Three letter id of the stop type.
     */
    public String getStopType() {
        return stopType;
    }

    /**
     * Accessor method for the type of the Bus stop.
     *
     * @return Three letter id of the bus stop type.
     */
    public String getBusStopType() {
        return busStopType;
    }

    /**
     * Accessor method for the timing status of the Bus stop.
     *
     * @return Three letter id of the timing status.
     */
    public String getTimingStatus() {
        return timingStatus;
    }
}
