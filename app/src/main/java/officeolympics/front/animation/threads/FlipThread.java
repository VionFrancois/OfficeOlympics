package officeolympics.front.animation.threads;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import officeolympics.front.animation.pageflip.FlipTransition;

public class FlipThread extends Thread {

    private static final int FLIP_DURATION = 1000;
    private int width, height;
    private Group node;

    public FlipThread(Group group, int width, int height) {
        this.node = group;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            FlipTransition.play(this.node, Duration.millis(FLIP_DURATION));
        });
    }

}
