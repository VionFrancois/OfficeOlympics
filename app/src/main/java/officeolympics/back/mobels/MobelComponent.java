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
}
