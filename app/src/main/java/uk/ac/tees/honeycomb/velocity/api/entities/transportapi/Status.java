package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * Container object for the {@link StatusValue} for a departure.
 */
public class Status {


    private StatusValue cancellation;


    /**
     * For the stop timetable endpoint; the status shows whether or not the departure is cancelled.
     * @return A {@link StatusValue} for a departure's cancellation.
     */
    public StatusValue getValue() {
        return cancellation;
    }
}
