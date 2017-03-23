package foodnow.foodnow.DatabaseModels;


import foodnow.foodnow.Models.LocationCoordinates;

/**
 * Created by vinee on 3/20/2017.
 */

public class RestaurantDB implements Comparable<RestaurantDB>{
    private String Name;
    private String OwnerId;
    private String Cuisine;
    private String Address;
    private String Phone;
    private LocationCoordinates Coordinates;
    private Double Distance;

    public RestaurantDB() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getDistance() {
        return Distance;
    }

    public void setDistance(Double distance) {
        Distance = distance;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public void setCuisine(String cuisine) {
        Cuisine = cuisine;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public LocationCoordinates getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(LocationCoordinates coordinates) {
        Coordinates = coordinates;
    }

    @Override
    public int compareTo(RestaurantDB o) {
        return Double.compare(this.Distance,o.Distance);
    }
}
