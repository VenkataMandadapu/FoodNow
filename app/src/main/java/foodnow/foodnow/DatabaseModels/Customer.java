package foodnow.foodnow.DatabaseModels;

/**
 * Created by vinee on 3/20/2017.
 */

public class Customer {
    private String CustomerId;
    private String CustomerName;

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }
    public String getCustomerId(String CustomerId) {
        return CustomerId;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerName(String CustomerName) {
        return CustomerName;
    }

}
