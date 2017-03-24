package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.UserTypeEnum;

/**
 * Created by vinee on 3/20/2017.
 */

public class OwnerDB {
    private String ownerId;
    private String ownerName;
    private UserTypeEnum userType;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}