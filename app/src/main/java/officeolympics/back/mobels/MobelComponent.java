package officeolympics.back.mobels;

public class MobelComponent {
    private int id;
    private MobelComponentLocation currentLocation;
    private MobelComponentLocation targetLocation;

    public MobelComponent(int id, MobelComponentLocation currentLocation, MobelComponentLocation targetLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.targetLocation = targetLocation;
    }

    public boolean isOnTarget() {
        return currentLocation.equals(targetLocation);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MobelComponentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(MobelComponentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public MobelComponentLocation getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(MobelComponentLocation targetLocation) {
        this.targetLocation = targetLocation;
    }
}
