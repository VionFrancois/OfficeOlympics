package officeolympics.front.controllers;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import officeolympics.front.scenes.Scenes;

public class Page1SceneController extends Controller {

    public void handleNextPage(MouseEvent mouseEvent) {
        this.pageFlip((Group) officeolympics.front.scenes.Scenes.Page1Scene.getRoot(), Scenes.B_Scene1);
    }
}
