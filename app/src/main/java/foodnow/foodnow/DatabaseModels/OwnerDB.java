package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.UserTypeEnum;

/**
 * Created by vinee on 3/20/2017.
 */

public class OwnerDB {
    private String OwnerId;
    private String OwnerName;
    private UserTypeEnum UserType;

    public void setOwnerId(String OwnerId) {
        this.OwnerId = OwnerId;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setUserType (UserTypeEnum UserType) {
        this.UserType = UserType;
    }

    public UserTypeEnum getUserType () {
        return UserType;
    }
}