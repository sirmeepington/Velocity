package uk.ac.tees.honeycomb.velocity.entities;

/**
 * Class to hold the longitude and latitude supplied by or given to the API for easier usage.
 */
public class Locations {
    private float longitude;
    private float latitude;

    /**
     * Accessor method to find the longitude of a Bus stop.
     *
     * @return Longitude of the Bus stop.
     */
    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * Accessor method to find the latitude of a Bus stop.
     *
     * @return Latitude of the Bus stop.
     */
    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
