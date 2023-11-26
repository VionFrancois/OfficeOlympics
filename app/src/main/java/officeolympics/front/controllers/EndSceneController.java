package officeolympics.front.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

import java.util.List;

public class EndSceneController extends Controller {

    @FXML
    public ImageView image;

    @FXML
    void initialize(){
        if(this.image.getImage() == null){
            this.image.setImage(Scenes.getImage("podium.png"));
        }
        this.image.setOpacity(0);
        FadeTransition FT = new FadeTransition(Duration.millis(5000), this.image);
        FT.setToValue(1);
        FT.setCycleCount(1);
        FT.setAutoReverse(false);
        FT.playFromStart();
    }

    public void handleMouseButtonClicked(MouseEvent mouseEvent) {

        Scenes.EndScreen2Scene = SceneLoader.load("EndScene2.fxml");
        this.pageFlip((Group) Scenes.EndScreenScene.getRoot(), Scenes.EndScreen2Scene);

    }
}
