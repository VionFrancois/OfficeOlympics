package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class TheEndController extends Controller {

    @FXML
    public Text yearsLaterText;

    @FXML
    void initialize() {
        TextCinematicController.setTextSpeed(150);
        TextCinematicController.setDelayBefore(1500);
        TextCinematicController.play("THE END", this.yearsLaterText);
    }

    public void handleYearsLaterLabelClicked(MouseEvent mouseEvent) {
    }
}
