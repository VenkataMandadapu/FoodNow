package foodnow.foodnow.Models;


/**
 * Created by vinee on 3/21/2017.
 */

public class LocationCoordinates {
    private double latitude;
    private double longitude;

    public LocationCoordinates() {

    }

    public LocationCoordinates(double mLatitude, double mLongitude) {
        this.latitude = mLatitude;
        this.longitude = mLongitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double mLatitude) {
        this.latitude = mLatitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double mLongitude) {
        this.longitude = mLongitude;
    }

}

