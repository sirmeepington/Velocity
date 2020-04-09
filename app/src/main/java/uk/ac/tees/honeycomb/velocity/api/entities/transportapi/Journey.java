package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * A Journey data class containing the information returned from the
 * {@link uk.ac.tees.honeycomb.velocity.api.entities.responses.JourneyResponse}.
 * This contains the {@link #request_time} as well as a list of {@link #routes}.
 */
public class Journey {

    private String acknowledgements;
    private String request_time;
    private JourneyRoute[] routes;
    private String source;

    /**
     * Gets a list of the {@link JourneyRoute}s in this Journey.
     * @return A list of {@link JourneyRoute}s.
     * @see JourneyRoute
     */
    public JourneyRoute[] getRoutes(){
        return routes;
    }

    /**
     * Provides any acknowledgements given by TransportAPI.
     * @return The acknowledgements from this journey.
     */
    public String getAcknowledgements(){
        return acknowledgements;
    }

    /**
     * Returns the request time as a string.
     * @return The request time.
     */
    public String getRequestTime(){
        return request_time;
    }

    /**
     * Returns the source from which TransportAPI has returned the
     * data from. This is different to what Impetus gives us.
     * @return The TransportAPI data source.
     */
    public String getSource(){
        return source;
    }

}

