package officeolympics.front.controllers;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import officeolympics.Main;
import officeolympics.front.scenes.Scenes;


public class MainScreen2Controller extends Controller {

    @FXML
    void handleNextPage(MouseEvent event) {
        Main.setScene(Scenes.Page1Scene);
        this.pageFlip((Group) Scenes.MainScreen2Scene.getRoot(), Scenes.Page1Scene);
    }

}
