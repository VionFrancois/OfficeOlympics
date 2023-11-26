package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

import java.util.function.Function;

public class YearsLaterController2 extends Controller{

    @FXML
    Text yearsLaterText;

    Function<Void, Void> callback;

    @FXML
    void initialize() {
        this.yearsLaterText.setText("4 years later...");
    }

    void animateText(String text, int delay, int delayBefore) {
        this.animateText(text, delay, delayBefore, null);
    }

    void animateText(String text, int delay, int delayBefore, Function<Void, Void> callback) {
        if (TextCinematicController.isRunning()) {
            TextCinematicController.stop();
            TextCinematicController.setDelayBefore(delayBefore);
        } else {
            TextCinematicController.setDelayBefore(delayBefore);
        }
        TextCinematicController.setTextSpeed(delay);
        TextCinematicController.play(text, this.yearsLaterText);
        this.callback = callback;
    }

    @FXML
    void handleYearsLaterLabelClicked() {
        if (this.callback != null) {
            this.callback.apply(null);
        } else {
            this.pageFlip((Group) Scenes.YearsLaterScene2.getRoot(), Scenes.ChairBuilderScene);
        }
    }

}
