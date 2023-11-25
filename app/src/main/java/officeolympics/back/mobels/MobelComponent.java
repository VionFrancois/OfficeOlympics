package officeolympics.back.mobels;

import java.util.ArrayList;

public class MobelComponent {
    private int id;
    private MobelComponentLocation currentLocation;
    private MobelComponentLocation targetLocation;
    private ArrayList<MobelComponentLocation> targetLocations;

    public MobelComponent(int id, MobelComponentLocation currentLocation, MobelComponentLocation targetLocation) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.targetLocation = targetLocation;
        System.out.println("building for current loc = " + currentLocation + " and target loc = " + targetLocation);
    }

    public MobelComponent(int id, MobelComponentLocation currentLocation, ArrayList<MobelComponentLocation> targetLocations) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.targetLocations = targetLocations;
        System.out.println("building for current loc = " + currentLocation + " and target loc = " + targetLocations);
    }

    public boolean isOnTarget() {
        if (targetLocation != null) return currentLocation.equals(targetLocation);
        return targetLocations.stream().anyMatch(item -> currentLocation.equals(item));
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

    public ArrayList<MobelComponentLocation> getTargetLocations() {
        return targetLocations;
    }

    public void setTargetLocations(ArrayList<MobelComponentLocation> targetLocations) {
        this.targetLocations = targetLocations;
    }

    @Override
    public String toString() {
        return "MobelComponent{" +
                "id=" + id +
                '}';
    }
}
