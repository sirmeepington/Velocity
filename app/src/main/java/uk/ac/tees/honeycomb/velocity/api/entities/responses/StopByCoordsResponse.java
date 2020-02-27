package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import java.util.List;

import uk.ac.tees.honeycomb.velocity.stops.NaptanBusStop;

public class StopByCoordsResponse {

    private List<NaptanBusStop> data;

    private String from;

    private double latitude;

    private double longitude;

    public List<NaptanBusStop> getData() {
        return data;
    }

    public String getDataSource() {
        return from;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
