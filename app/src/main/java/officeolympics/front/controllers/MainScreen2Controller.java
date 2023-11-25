package officeolympics.front.controllers;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import officeolympics.Main;
import officeolympics.front.scenes.Scenes;


public class MainScreen2Controller extends Controller {

    @FXML
    void handleNextPage(MouseEvent event) {
        Main.setScene(Scenes.Page1Scene);
        // nextScene = scene; // Tkt
        // Main.setScene(nextScene);
    }

}
