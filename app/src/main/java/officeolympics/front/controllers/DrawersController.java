package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DrawersController extends Controller {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Pane drawersPane;
    @FXML
    ImageView drawersImageView;

    @FXML
    private void drawersClicked(MouseEvent event) {
        double x = event.getX(), y = event.getY();

    }

    @FXML
    private void firstDrawerClicked() {
        drawersImageView = new ImageView(new Image("images/meubles/alex1.png"));
        drawersPane.getChildren().remove(0);
        drawersPane.getChildren().add(drawersImageView);
    }

    @FXML
    private void secondDrawerClicked() {
        drawersImageView = new ImageView(new Image("images/alex2.png"));
        drawersPane.getChildren().remove(0);
        drawersPane.getChildren().add(drawersImageView);
    }

    @FXML
    private void thirdDrawerClicked() {
        drawersImageView = new ImageView(new Image("images/alex3.png"));
        drawersPane.getChildren().remove(0);
        drawersPane.getChildren().add(drawersImageView);
    }

    @FXML
    private void fourthDrawerClicked() {
        drawersImageView = new ImageView(new Image("images/alex4.png"));
        drawersPane.getChildren().remove(0);
        drawersPane.getChildren().add(drawersImageView);
    }

    @FXML
    private void fifthDrawerClicked() {
        drawersImageView = new ImageView(new Image("images/alex5.png"));
        drawersPane.getChildren().remove(0);
        drawersPane.getChildren().add(drawersImageView);
    }
}
