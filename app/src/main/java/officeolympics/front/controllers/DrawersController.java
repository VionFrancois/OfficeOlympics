package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import officeolympics.front.scenes.Scenes;

public class DrawersController extends Controller {
    @FXML
    ImageView drawersImageView;
    int count = 0;

    @FXML
    private void drawersClicked(MouseEvent event) {
        System.out.println("drawers clicked");
        switch (count) {
            case 0 -> {
                firstDrawerClicked();
                count++;
            }
            case 1 -> {
                secondDrawerClicked();
                count++;
            }
            case 2 -> {
                thirdDrawerClicked();
                count++;
            }
            case 3 -> {
                fourthDrawerClicked();
                count = 0;
                this.pageFlip((Group) Scenes.DrawersScene.getRoot(), Scenes.B_Scene3);
            }
        }
    }

    @FXML
    private void firstDrawerClicked() {
        drawersImageView.setImage(new Image("images/alex1.png"));
    }

    @FXML
    private void secondDrawerClicked() {
        drawersImageView.setImage(new Image("images/alex2.png"));
    }

    @FXML
    private void thirdDrawerClicked() {
        drawersImageView.setImage(new Image("images/alex3.png"));
    }

    @FXML
    private void fourthDrawerClicked() {
        drawersImageView.setImage(new Image("images/alex4.png"));
    }

    @FXML
    private void fifthDrawerClicked() {
        drawersImageView.setImage(new Image("images/alex5.png"));
    }
}
