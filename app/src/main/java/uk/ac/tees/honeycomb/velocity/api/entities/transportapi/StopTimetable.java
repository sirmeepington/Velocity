package uk.ac.tees.honeycomb.velocity.api.entities.transportapi;

import java.util.ArrayList;

import uk.ac.tees.honeycomb.velocity.entities.Bearing;

/**
 * A POJO class for storing a stop's timetable given by Transport API.
 */
public class StopTimetable {

    private String atcocode;
    private String bearing;
    private String indicator;
    private String locality;
    private Location location;
    private String name;
    private String request_time;
    private String smscode;
    private String source;
    private String stop_name;
    private ArrayList<Departure> departures;

    /**
     * Returns the ATCO code for the stop whose timetable this is.
     * @return
     */
    public String getAtcoCode() {
        return atcocode;
    }

    /**
     * Returns the {@link uk.ac.tees.honeycomb.velocity.entities.Bearing} of this stop.
     * @return
     */
    public Bearing getBearing() {
        return Bearing.valueOf(bearing);
    }

    /**
     * The indicator of the stop.
     * @return The stop's indicator/
     */
    public String getIndicator() {
        return indicator;
    }

    /**
     * The locality name for this stop.
     * @return
     */
    public String getLocality() {
        return locality;
    }

    /**
     * The TransportAPI {@link Location} for this object.
     * @return
     */
    public Location getTransportApiLocation() {
        return location;
    }

    /**
     * The Location entity of the bus stop.
     * @return The bus stop's location.
     */
    public uk.ac.tees.honeycomb.velocity.entities.Location getLocation(){
        return new uk.ac.tees.honeycomb.velocity.entities.Location(location);
    }

    /**
     * Gets the full name of the bus stop; this includes the identifier.
     * @return The bus stop's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the easily-readable SMSCode for the stop.
     * @return The SMS code for the stop.
     */
    public String getSmsCode() {
        return smscode;
    }


    public String getSource() {
        return source;
    }

    /**
     * Gets the name of the stop.
     * @return The stop's name.
     */
    public String getStopName() {
        return stop_name;
    }

    /**
     * Gets a list of {@link Departure}s from the {@link DepartureContainer}.
     * @return A list of departures.
     */
    public ArrayList<Departure> getDepartures() {
        return departures;
    }
}
