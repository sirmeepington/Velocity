package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * A class to contain the reason and value of a status.
 */
public class StatusValue {

    private String reason;
    private boolean value;

    /**
     * The reason for why the value is true. This will be null if the value is false.
     * @return The reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Returns the value boolean.
     * @return The status value.
     */
    public boolean getValue() {
        return value;
    }
}
