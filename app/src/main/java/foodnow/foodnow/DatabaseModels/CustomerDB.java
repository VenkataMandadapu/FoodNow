package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.UserTypeEnum;

/**
 * Created by vinee on 3/20/2017.
 */

public class CustomerDB {
    private String customerId;
    private String customerName;
    private UserTypeEnum userType;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
