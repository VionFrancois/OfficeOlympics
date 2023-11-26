package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import officeolympics.Main;
import officeolympics.front.navigation.Flow;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

import java.util.function.Function;

public class YearsLaterController extends Controller{

    @FXML
    Text yearsLaterText;

    Function<Void, Void> callback;

    @FXML
    void initialize() {
        this.yearsLaterText.setText("6 months later...");
        this.callback = (e) -> {
            this.animateText("Il est l'heure de montrer au monde mon experience.", 35, 300,
                    (e2) -> {
                    this.animateText("Début de l’épreuve, 1 meuble à monter le plus vite possible.", 20, 200);
                    return null;
                });
            return null;
        };
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
            this.pageFlip((Group) Scenes.YearsLaterScene.getRoot(), Scenes.TableBuilderScene);
        }
    }

}
