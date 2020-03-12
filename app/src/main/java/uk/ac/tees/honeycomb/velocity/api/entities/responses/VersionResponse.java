package uk.ac.tees.honeycomb.velocity.api.entities.responses;


/**
 * A response from the Impetus endpoint of {@code /} or {@code /impetus/version/}.
 * @author Aidan
 */
public class VersionResponse {

    /**
     * The version string returned from Impetus.
     */
    private String version;

    /**
     * Returns the version string given from the back-end system.
     * @return The version of the back-end system.
     */
    public String getVersion() {
        return version;
    }
}
