package foodnow.foodnow.DatabaseModels;

/**
 * Created by vinee on 3/20/2017.
 */

public class RestaurantDB {
    private int RestaurantId;
    private String RestaurantName;
    private String OwnerId;
    private String Cuisine;
    private String Address;
    private String Phone;

    public void setRestaurantId(int RestaurantId) {
        this.RestaurantId = RestaurantId;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantName(String RestaurantName) {
        this.RestaurantName = RestaurantName;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setOwnerId(String OwnerId) {
        this.OwnerId = OwnerId;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setCuisine(String Cuisine) {
        this.Cuisine = Cuisine;
    }

    public String getCuisine() {
        return Cuisine;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getAddress() {
        return Address;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getPhone() {
        return Phone;
    }
}
