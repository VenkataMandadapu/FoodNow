package foodnow.foodnow.DatabaseModels;

/**
 * Created by vinee on 3/20/2017.
 */

public class Owner {
    private String OwnerId;
    private String OwnerName;

    public void setOwnerId(String OwnerId) {
        this.OwnerId = OwnerId;
    }
    public String getOwnerId(String OwnerId) {
        return OwnerId;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }

    public String getOwnerName(String OwnerName) {
        return OwnerName;
    }

}

