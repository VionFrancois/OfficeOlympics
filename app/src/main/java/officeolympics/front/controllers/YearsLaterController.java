package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import officeolympics.front.navigation.Flow;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

public class YearsLaterController extends Controller{

    @FXML
    Text yearsLaterText;

    @FXML
    void initialize() {
        this.yearsLaterText.setText("4 years later...");
    }

    @FXML
    void handleYearsLaterLabelClicked() {
        // Navigate to the end screen
        Scenes.EndScreenScene = SceneLoader.load("EndScreenScene.fxml");

        Flow.add(Scenes.EndScreenScene);

        this.pageFlip((Group) Scenes.YearsLaterScene.getRoot(), Scenes.EndScreenScene);
    }

}
