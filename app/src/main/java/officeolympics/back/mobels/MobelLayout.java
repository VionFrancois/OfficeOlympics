package officeolympics.back.mobels;

import java.util.ArrayList;

public class MobelLayout {
    private ArrayList<MobelComponent> mobelComponents;

    public MobelLayout(ArrayList<MobelComponent> mobelComponents) {
        this.mobelComponents = mobelComponents;
    }

    public boolean isLayoutFilled() {
        // For all mobelComponent in the mobelComponents ArrayList, check if they are on target
        // (we define what "on target" is, it could be defined as "at most 10 pixels away in each direction".
        return mobelComponents.stream().allMatch(MobelComponent::isOnTarget);
    }

    public ArrayList<MobelComponent> getMobelComponents() {
        return mobelComponents;
    }

    public void setMobelComponents(ArrayList<MobelComponent> mobelComponents) {
        this.mobelComponents = mobelComponents;
    }
}
