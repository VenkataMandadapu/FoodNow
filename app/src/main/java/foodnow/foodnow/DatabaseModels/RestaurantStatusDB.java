package foodnow.foodnow.DatabaseModels;
import foodnow.foodnow.Models.CapacityStatusEnum;
import foodnow.foodnow.Models.UserTypeEnum;
import foodnow.foodnow.Models.WaitStatusEnum;
import foodnow.foodnow.Models.LocationCoordinates;
/**
 * Created by vinee on 3/21/2017.
 */

public class RestaurantStatusDB {
    private String name;
    private CapacityStatusEnum capacityStatus;
    private WaitStatusEnum waitStatus;
    private int numberOfUpdates;
    private Long timeStamp;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public RestaurantStatusDB() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CapacityStatusEnum getCapacityStatus() {
        return capacityStatus;
    }

    public void setCapacityStatus(CapacityStatusEnum capacityStatus) {
        this.capacityStatus = capacityStatus;
    }

    public WaitStatusEnum getWaitStatus() {
        return waitStatus;
    }

    public void setWaitStatus(WaitStatusEnum waitStatus) {
        this.waitStatus = waitStatus;
    }

    public int getNumberOfUpdates() {
        return numberOfUpdates;
    }

    public void setNumberOfUpdates(int numberOfUpdates) {
        this.numberOfUpdates = numberOfUpdates;
    }
}
