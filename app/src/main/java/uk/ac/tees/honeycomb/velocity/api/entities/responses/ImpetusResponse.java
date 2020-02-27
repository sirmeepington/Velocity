package uk.ac.tees.honeycomb.velocity.api.entities.responses;

/**
 * The root-level response layout for Impetus requests.
 * The generic argument {@link T} signifies the data type in {@code message}.
 * @author Aidan
 */
public class ImpetusResponse<T> {

    /**
     * The message that the system has returned to the user.
     */
    private T message;
    /**
     * The HTTP status as a string that has been returned.
     */
    private String status;

    /**
     * Returns the HTTP status from the response.
     * @return The HTTP status code's meaning as a string. (e.g OK, Not Found, Bad Request)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the message from the system. This contains the relevant information that has been
     * returned to the user.
     * @return The message from Impetus.
     */
    public T getMessage() {
        return message;
    }
}
