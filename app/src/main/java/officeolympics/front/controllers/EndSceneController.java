package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import officeolympics.front.animation.FadeInTransition;
import officeolympics.front.scenes.Scenes;

public class EndSceneController extends Controller {

    @FXML
    public ImageView image;

    @FXML
    void initialize(){
        if(this.image.getImage() == null){
            this.image.setImage(Scenes.getImage("podium.png"));
        }
        FadeInTransition.playFromStartOn(this.image, Duration.millis(2000));
    }

}
