package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.UserTypeEnum;

/**
 * Created by vinee on 3/20/2017.
 */

public class CustomerDB {
    private String CustomerId;
    private String CustomerName;
    private UserTypeEnum UserType;

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setUserType (UserTypeEnum UserType) {
        this.UserType = UserType;
    }

    public UserTypeEnum getUserType () {
        return UserType;
    }
}
