package uk.ac.tees.honeycomb.velocity.entities;

import java.util.Locale;

/**
 * Class to hold the longitude and latitude supplied by or given to the API for easier usage.
 */
public class Location {
    private final double longitude;
    private final double latitude;

    public Location(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(uk.ac.tees.honeycomb.velocity.api.entities.transportapi.Location location){
        this.longitude = location.getLocation().longitude;
        this.latitude = location.getLocation().latitude;
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


    /**
     * Returns a formatted string depicting this location as usable for the Journey Planner.
     * The format is as such: {@code longlat:0.00000,0.00000} for longitude and latitude
     * respectfully.
     * @return The formatted string for this location.
     * @see uk.ac.tees.honeycomb.velocity.api.entities.endpoints.JourneyFromCoords
     */
    public String getLongLat(){
        return String.format(Locale.ENGLISH,"lonlat:%f,%f", getLongitude(), getLatitude());
    }

}
