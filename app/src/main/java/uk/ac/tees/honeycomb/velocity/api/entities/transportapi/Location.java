package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * A TransportAPI location container that contains the coordinates in a double array.
 */
public class Location {

    private String type;
    private double[] coordinates;


    /**
     * The location type. Usually a Point.
     * @return The location type.
     */
    public String getType() {
        return type;
    }

    /**
     * Converts the double-array coordinates in this class to a
     * {@link uk.ac.tees.honeycomb.velocity.entities.Location} object and returns it.
     * @return The location object.
     */
    public uk.ac.tees.honeycomb.velocity.entities.Location getLocation(){
        return new uk.ac.tees.honeycomb.velocity.entities.Location(coordinates[0],coordinates[1]);
    }
}
