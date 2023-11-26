package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

public class EndScene2Controller extends Controller {


    @FXML
    public ImageView image;
    @FXML
    public Text paragraph;

    @FXML
    void initialize() {
        if (this.image.getImage() == null) {
            this.image.setImage(Scenes.getImage("debt.png"));
        }
        TextCinematicController.play("Han tog sin hämnd och visade för hela världen svenskarnas bedrägeri.", this.paragraph);
    }

    @FXML
    public void handlePaneMouseClicked(MouseEvent mouseEvent) {
        Scenes.TheEndScene = SceneLoader.load("TheEndScene.fxml");
        this.pageFlip((Group) Scenes.EndScreen2Scene.getRoot(), Scenes.TheEndScene);
    }
}
