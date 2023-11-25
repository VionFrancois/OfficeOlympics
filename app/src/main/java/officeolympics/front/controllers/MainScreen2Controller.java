package officeolympics.front.controllers;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.MouseEvent;

import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import officeolympics.Main;


public class MainScreen2Controller extends Controller {

    @FXML
    void handleNextPage(MouseEvent event){
        nextScene = scene; // Tkt
        Main.setScene(nextScene);
    }

}
