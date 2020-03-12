package uk.ac.tees.honeycomb.velocity.api.entities;

/**
 * An object describing the status of back-end and external services such as the database which
 * holds cached data as well as the external Transport API service status.
 * @author Aidan
 */
public class ServiceStatus {

    private String database;
    private String impetus;
    private String transportapi;

    /**
     * Gets the {@link StatusType} of the database.
     * @return The {@link StatusType} of the database.
     */
    public StatusType getDatabaseStatus() {
        return StatusType.fromString(database);
    }

    /**
     * Gets the {@link StatusType} of Impetus; this should always be {@link StatusType#OK}.
     * @return The {@link StatusType} of Impetus.
     */
    public StatusType getImpetusStatus() {
        return StatusType.fromString(impetus);
    }


    /**
     * Gets the {@link StatusType} of the third party TransportAPI service.
     * @return The {@link StatusType} of the third party TransportAPI service.
     */
    public StatusType getTransportapiStatus() {
        return StatusType.fromString(transportapi);
    }
}
