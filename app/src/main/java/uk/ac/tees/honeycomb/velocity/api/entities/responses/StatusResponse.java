package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import uk.ac.tees.honeycomb.velocity.api.entities.ServiceStatus;

/**
 * A response from the {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.ImpetusStatus}
 * endpoint which provides a {@link ServiceStatus} object to view individual statuses of the
 * back-end services.
 * @author Aidan
 */
public class StatusResponse {

    private ServiceStatus serviceStatus;

    /**
     * Retrieves the back-end service information via the stored {@link ServiceStatus};
     * @return The {@link ServiceStatus} that has been created via gson.
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }
}
