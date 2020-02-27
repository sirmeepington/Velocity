package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.BusStop;
import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class StopByNameResponse {

    List<NaptanBusStop> data;

    String from;

    public List<NaptanBusStop> getStops(){
        return data;
    }

    public String getDataSource(){
        return from;
    }

}
