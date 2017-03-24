package foodnow.foodnow.Models;


/**
 * Created by vinee on 3/21/2017.
 */

public class LocationCoordinates {
    private double latitude;
    private double longitude;

    public LocationCoordinates(double mLatitude, double mLongitude) {
        this.latitude = mLatitude;
        this.longitude = mLongitude;
    }

    public LocationCoordinates() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

