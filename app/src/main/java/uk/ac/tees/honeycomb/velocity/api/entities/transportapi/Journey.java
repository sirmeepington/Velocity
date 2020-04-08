package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

public class Journey {

    private String acknowledgements;
    private String request_time;
    private JourneyRoute[] routes;
    private String source;

    public JourneyRoute[] getRoutes(){
        return routes;
    }

    public String getAcknowledgements(){
        return acknowledgements;
    }

    public String getRequestTime(){
        return request_time;
    }

    public String getSource(){
        return source;
    }

}

