package uk.ac.tees.honeycomb.velocity.entities;

import org.xml.sax.helpers.LocatorImpl;

/**
 * Class to hold the longitude and latitude supplied by or given to the API for easier usage.
 */
public class Locations {
    private final double longitude;
    private final double latitude;

    public Locations(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * Accessor method to find the longitude of a Bus stop.
     *
     * @return Longitude of the Bus stop.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Accessor method to find the latitude of a Bus stop.
     *
     * @return Latitude of the Bus stop.
     */
    public double getLatitude() {
        return latitude;
    }
}
