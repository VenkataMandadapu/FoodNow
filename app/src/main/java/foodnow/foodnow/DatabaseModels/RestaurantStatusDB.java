package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.CapacityStatusEnum;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.Models.WaitStatusEnum;

/**
 * Created by vinee on 3/21/2017.
 */

public class RestaurantStatusDB {
    private int RestaurantId;
    private String UserId;
    private CapacityStatusEnum CapacityStatus;
    private WaitStatusEnum WaitStatus;
    private int NumberOfUpdates;
    private String TimeStamp;
    private UserTypeEnum UserType;

    public void setRestaurantId(int RestaurantId) {
        this.RestaurantId = RestaurantId;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setCapacityStatus(CapacityStatusEnum CapacityStatus) {
        this.CapacityStatus = CapacityStatus;
    }

    public CapacityStatusEnum getCapacityStatus() {
        return CapacityStatus;
    }

    public void setWaitStatus(WaitStatusEnum WaitStatus) {
        this.WaitStatus = WaitStatus;
    }

    public WaitStatusEnum getWaitStatus() {
        return WaitStatus;
    }

    public void setNumberOfUpdates(int NumberOfUpdates) {
        this.NumberOfUpdates = NumberOfUpdates;
    }

    public int getNumberOfUpdates() {
        return NumberOfUpdates;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setUserType (UserTypeEnum UserType) {
        this.UserType = UserType;
    }

    public UserTypeEnum getUserType () {
        return UserType;
    }

}
