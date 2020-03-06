package uk.ac.tees.honeycomb.velocity.api.entities;

/**
 * An enumeration type showing the expected statuses of a service.
 * @author Aidan
 */
public enum StatusType {
    /**
     * The service is accessible.
     */
    OK,
    /**
     * The service is not accessible.
     */
    FAIL,
    /**
     * The service status is unknown or is not being tracked.
     */
    UNKNOWN;

    /**
     * Converts a {@link String} representation of the {@link StatusType} to the enumeration
     * representation. This is useful due to Gson and how it handles enumeration types.
     * @param in The string representation of the value.
     * @return The {@link StatusType} enumeration type of the value {@code in}.
     */
    public static StatusType fromString(String in){
        switch(in.toLowerCase()){
            case "ok":
                return StatusType.OK;
            case "fail":
                return StatusType.FAIL;
            default:
                return StatusType.UNKNOWN;
        }
    }
}
