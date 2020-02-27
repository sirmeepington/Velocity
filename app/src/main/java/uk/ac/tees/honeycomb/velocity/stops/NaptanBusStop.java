package uk.ac.tees.honeycomb.velocity.stops;

import android.location.Location;
import android.os.strictmode.NonSdkApiUsedViolation;

import uk.ac.tees.honeycomb.velocity.entities.Bearing;
import uk.ac.tees.honeycomb.velocity.entities.Locations;

/**
 * Class to interact with the NapTAN API to request and retrieve data.
 * Implements the Bus Stop interface.
 * @author Jason - (GSON compliancy by Aidan)
 */
public class NaptanBusStop implements BusStop {

    // JSON compliant instance variables.
    // Naming MUST be the same as the JSON and typing MUST be a String
    // else Gson fails to serialise properly and causes a whole lot of problems.
    // It took me 3 hours to find out the problem. - Aidan
    private String ATCOCode;
    private String AdministrativeAreaCode;
    private String Bearing;
    private String BusStopType;
    private String CleardownCode;
    private String CommonName;
    private String CommonNameLang;
    private String CreationDateTime;
    private String Crossing;
    private String CrossingLang;
    private String DefaultWaitTime;
    private String Easting;
    private String GrandParentLocalityName;
    private String GridType;
    private String Indicator;
    private String IndicatorLang;
    private String Landmark;
    private String LandmarkLang;
    private String Latitude;
    private String LocalityCentre;
    private String LocalityName;
    private String Longitude;
    private String Modification;
    private String ModificationDateTime;
    private String NaptanCode;
    private String Northing;
    private String Notes;
    private String NotesLang;
    private String NptgLocalityCode;
    private String ParentLocalityName;
    private String PlateCode;
    private String RevisionNumber;
    private String ShortCommonName;
    private String ShortCommonNameLang;
    private String Status;
    private String StopType;
    private String Street;
    private String StreetLang;
    private String Suburb;
    private String SuburbLang;
    private String TimingStatus;
    private String Town;
    private String TownLang;

    /**
     * Accessor method for the Bus Stop identifier for use with Transport API and Naptan Database.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getAtcoCode() {
        return ATCOCode;
    }

    /**
     * Accessor method for the shorthand for ATCO code.
     *
     * @return Unique String id for Bus stops.
     */
    @Override
    public String getSmsCode() {
        return NaptanCode;
    }

    /**
     * Accessor method for the name of the area
     *
     * @return String of the Bus stop area.
     */
    @Override
    public String getName() {
        return CommonName;
    }

    /**
     * Accessor method for the Latitude and Longitude values for the Bus stop.
     *
     * @return Locations class containing Latitude and Longitude.
     */
    @Override
    public Locations getLocation() {

        try {
            double longitude = Double.parseDouble(Longitude);
            double latitude = Double.parseDouble(Latitude);
            return new Locations(longitude,latitude);
        } catch (NumberFormatException ex){
            return null;
        }
    }

    /**
     * Accessor method for the Bearing of the Bus stop.
     *
     * @return Bearing enum indicating the bearing of the bus stop.
     */
    @Override
    public Bearing getBearing() {
        return Enum.valueOf(Bearing.class,Bearing);
    }

    /**
     * Accessor method for the Indicator for which direction the Bus is travelling.
     *
     * @return String of the indicator.
     */
    @Override
    public String getIndicator() {
        return IndicatorLang;
    }

    /**
     * Accessor method for the Locality/Location of the Bus stop.
     *
     * @return General area and Town name of the Bus stop.
     */
    @Override
    public String getLocality() {
        return LocalityName;
    }

    /**
     * Accessor method for the Street of the Bus stop.
     *
     * @return The street name the Bus stop is located on.
     */
    public String getStreet() { return Street;}

    /**
     * Accessor method or the Locality code of the Bus stop locality.
     *
     * @return
     */
    public String getNptgLocalityCode() {
        return NptgLocalityCode;
    }

    /**
     * Accessor method for the stop type provided by the API.
     * We are only interested in stops labelled "BCT".
     *
     * @return Three letter id of the stop type.
     */
    public String getStopType() {
        return StopType;
    }

    /**
     * Accessor method for the type of the Bus stop.
     *
     * @return Three letter id of the bus stop type.
     */
    public String getBusStopType() {
        return BusStopType;
    }

    /**
     * Accessor method for the timing status of the Bus stop.
     *
     * @return Three letter id of the timing status.
     */
    public String getTimingStatus() {
        return TimingStatus;
    }
}
