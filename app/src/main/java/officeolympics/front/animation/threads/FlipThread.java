package officeolympics.front.animation.threads;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import officeolympics.front.animation.pageflip.FlipTransition;

import java.util.concurrent.locks.ReentrantLock;

public class FlipThread extends Thread {

    private static final int FLIP_DURATION = 1000;
    private int width, height;
    private Group node;
    private Scene target;
    private static final ReentrantLock lock = new ReentrantLock();

    public FlipThread(Group group, int width, int height, Scene target) {
        this.node = group;
        this.width = width;
        this.height = height;
        this.target = target;
    }

    @Override
    public void run() {
        lock.lock();
        Platform.runLater(() -> {
            FlipTransition.play(this.node, Duration.millis(FLIP_DURATION), this.target);
        });
        lock.unlock();
    }

}
