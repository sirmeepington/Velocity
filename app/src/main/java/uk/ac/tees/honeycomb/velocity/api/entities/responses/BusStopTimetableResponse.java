package uk.ac.tees.honeycomb.velocity.api.entities.responses;

import uk.ac.tees.honeycomb.velocity.api.entities.transportapi.StopTimetable;

/**
 * A response for the {@link uk.ac.tees.honeycomb.velocity.api.entities.endpoints.StopTimetable}
 * endpoint.
 */
public class BusStopTimetableResponse {

    private String atco;
    private StopTimetable data;

    /**
     * Returns the ATCO code specified during input.
     * @return The ATCO code specified.
     */
    public String getAtcoCode() {
        return atco;
    }

    /**
     * Gets the {@link StopTimetable} from TransportAPI.
     * @return The Stop Timetable.
     */
    public StopTimetable getStopTimetable() {
        return data;
    }
}
