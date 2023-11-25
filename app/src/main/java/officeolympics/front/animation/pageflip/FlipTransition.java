package officeolympics.front.animation.pageflip;

import javafx.animation.*;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.shape.Rectangle;
import officeolympics.Main;
import officeolympics.front.animation.FadeInTransition;
import officeolympics.front.animation.threads.FlipThread;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import org.checkerframework.checker.units.qual.C;

/**
 * This class is used to make use of a flip transition. It creates a new <code>Thread</code> and uses it
 * to properly flip the given <code>Node</code>, while the main thread can keep going on its own, independently of
 * the flip transition.
 * <br>
 * The flip transition is a transition that makes a node flip from one side to the other, as if it was a page of a book.
 */
public class FlipTransition {

    private static final int SHADOW_RATIO = 30;

    /**
     * Starts a flip transition on the given <code>Node</code> and for the given <code>Duration</code>, during which
     * the node slowly flips from one side to the other, at constant pace.
     *
     * @param group    the group on which the transition should be applied
     * @param duration the duration of the transition
     */
    public static void play(Group group, Duration duration, Scene target) {
        FlipTransition._playFromStartOn(group, duration, target);
    }

    /**
     * Starts a flip transition on the given <code>Node</code> and for the given <code>Duration</code>, during which
     * the node slowly flips from one side to the other, at constant pace.
     *
     * @param group    the group on which the transition should be applied
     * @param duration the duration of the transition
     */
    private static void _playFromStartOn(Group group, Duration duration, Scene target) {
        ImageView targetImage = new ImageView(target.getRoot().snapshot(new SnapshotParameters(), null));

        int width = Main.WIDTH;
        int height = Main.HEIGHT;

        Rectangle clip = new Rectangle(0, 0, width, height);
        Node node = group.getChildren().get(group.getChildren().size() - 1);
        node.setClip(clip);

        TranslateTransition clipTranslation = new TranslateTransition(duration, clip);
        clipTranslation.setToX(-width);
        clipTranslation.setInterpolator(Interpolator.EASE_IN);

        // Back of the page
        Group flipGroup = new Group();

        Rectangle flipClip = new Rectangle(0, 0, width, height);
        flipClip.setFill(Color.WHITE);
        flipGroup.getChildren().add(flipClip);

        // Image on back of page
        ImageView flipImage = new ImageView(node.snapshot(new SnapshotParameters(), null));
        flipImage.setRotationAxis(new Point3D(0, 1, 0));
        flipImage.setRotate(180);
        flipImage.setOpacity(.4);
        flipGroup.getChildren().add(flipImage);

        Rectangle flipPage = new Rectangle(0, 0, width, height);
        flipGroup.setClip(flipPage);

        group.getChildren().add(flipGroup);

        TranslateTransition flipTranslation = new TranslateTransition(duration, flipGroup);
        flipTranslation.setFromX(width);
        flipTranslation.setToX(-width);
        flipTranslation.setInterpolator(Interpolator.EASE_IN);

        TranslateTransition clipTranslation2 = new TranslateTransition(duration, flipPage);
        clipTranslation2.setFromX(-width);
        clipTranslation2.setToX(0);
        clipTranslation2.setInterpolator(Interpolator.EASE_IN);

        // Pages shadow
        Rectangle pageShadow = new Rectangle(width - SHADOW_RATIO, 0, SHADOW_RATIO*2, height);
        LinearGradient pageShadowGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new javafx.scene.paint.Stop(0, Color.rgb(0, 0, 0, .15)),
                new javafx.scene.paint.Stop(.8, Color.TRANSPARENT),
                new javafx.scene.paint.Stop(1, Color.rgb(0, 0, 0, .15)));
        pageShadow.setFill(pageShadowGradient);

        group.getChildren().add(pageShadow);

        // Shadow animations
        ScaleTransition shadowScale = new ScaleTransition(Duration.millis(duration.toMillis()/5), pageShadow);
        shadowScale.setFromX(0);
        shadowScale.setToX(1);
        shadowScale.setInterpolator(Interpolator.EASE_IN);
        TranslateTransition shadowTranslate = new TranslateTransition(duration, pageShadow);
        shadowTranslate.setToX(-width);
        shadowTranslate.setInterpolator(Interpolator.EASE_IN);
        ScaleTransition shadowScale2 = new ScaleTransition(Duration.millis(duration.toMillis()/5), pageShadow);
        shadowScale2.setFromX(1);
        shadowScale2.setToX(0);
        shadowScale2.setInterpolator(Interpolator.EASE_OUT);
        SequentialTransition shadowTransition = new SequentialTransition(new PauseTransition(Duration.millis(duration.toMillis()*4/5)), shadowScale2);
        ParallelTransition globalTransition = new ParallelTransition(
                clipTranslation,
                flipTranslation,
                clipTranslation2,
                shadowScale, shadowTranslate, shadowTransition);

        // Add the target image behind the flip transition
        group.getChildren().add(targetImage);
        targetImage.toBack();

        globalTransition.setOnFinished(event -> {
            group.getChildren().remove(pageShadow);
            group.getChildren().remove(flipGroup);
            node.setClip(null);
            group.getChildren().remove(node);
            group.getChildren().add(node);
            Main.setScene(target);
        });

        globalTransition.play();
    }

}
