package foodnow.foodnow.Models;


/**
 * Created by vinee on 3/21/2017.
 */

public class LocationCoordinates {
    private double Latitude;
    private double Longitude;

    public LocationCoordinates(double mLatitude, double mLongitude) {
        this.Latitude = mLatitude;
        this.Longitude = mLongitude;
    }

    public LocationCoordinates() {
    }

    public double getLatitude() {

        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}

