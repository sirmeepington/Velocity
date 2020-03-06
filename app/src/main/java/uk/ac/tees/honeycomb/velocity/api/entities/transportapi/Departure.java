package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

/**
 * An object storing the information about the bus that departs from a stop.
 */
public class Departure {

    private String aimed_departure_time;
    private String best_departure_estimate;
    private String date;
    private String dir;
    private String direction;
    private String expected_departure_date;
    private String expected_departure_time;
    private String line;
    private String line_name;
    private String mode;
    private String operator;
    private String operator_name;
    private String source;
    private Status status;


    /**
     * The time that the departure is estimated to occur at.
     * @return
     */
    public String getAimedDepartureTime() {
        return aimed_departure_time;
    }

    /**
     * The date of the departure; for buses this should match todays date.
     * @return The date of the departure.
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the inbound/outbound direction of the departure.
     * @return Inbound or Outbound
     */
    public String getDir() {
        return dir;
    }

    /**
     * The end stop in the direction of {@link #dir}.
     * @return The end bus stop.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Returns the line number (12, 63A, 63, etc.)
     * @return
     */
    public String getLine() {
        return line;
    }

    /**
     * Returns the name of the line; sometimes the same as {@link #getLine()}
     * @return The line name.
     */
    public String getLineName() {
        return line_name;
    }

    /**
     * The operating code of the bus in this departure.
     * @return The operator code.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * The name of the operator of the bus in this departure.
     * @return The operator name.
     */
    public String getOperatorName() {
        return operator_name;
    }

    /**
     * Returns the TransportAPI {@link Status} object for this departure.
     * @return A TransportAPI status object.
     */
    public Status getStatus() {
        return status;
    }
}
