/**
 * This enum class defines the locations of fitness classes.
 * @author Ramitha Ravishankar, Gloria Liu
 */
public enum Location {
    BRIDGEWATER ("08807", "SOMERSET"),
    EDISON ("08837", "MIDDLESEX"),
    FRANKLIN ("08873", "SOMERSET"),
    PISCATAWAY ("08854", "MIDDLESEX"),
    SOMERVILLE ("08876", "SOMERSET");

    private final String zip;
    private final String county;

    /**
     * Parameterized constructor to build a Location enum object
     * @param zip the zipcode of the city
     * @param county the county of the city
     */
    Location(String zip, String county) {
        this.zip = zip;
        this.county = county.toUpperCase();
    }

    /**
     * Gets the county of the Location object.
     * @return the Location object's county.
     */
    public String getCounty(){
        return this.county;
    }

    /**
     * Gets the zip of the Location object.
     * @return the Location object's zip.
     */
    public String getZip(){
        return this.zip;
    }

    /**
     * Converts the location to string form.
     * @return the location in string form, with city, zip code, and county.
     */
    @Override
    public String toString(){
        switch(this) {
            case BRIDGEWATER: return "BRIDGEWATER, " + this.zip + ", " + this.county;
            case EDISON: return "EDISON, " + this.zip + ", " + this.county;
            case FRANKLIN: return "FRANKLIN, " + this.zip + ", " + this.county;
            case PISCATAWAY: return "PISCATAWAY, " + this.zip + ", " + this.county;
            case SOMERVILLE: return "SOMERVILLE, " + this.zip + ", " + this.county;
            default: return null;
        }
    }
}