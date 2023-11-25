package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import officeolympics.Main;
import officeolympics.front.scenes.Scenes;

import javax.swing.text.html.ImageView;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

public class B_Scene1Controller extends Controller {

    @FXML
    public Text paragraph;

    private final List<String> texts = List.of(
            "La suède est devenue l’une des plus grandes puissances mondiales, en particulier, IKEA a dépassé de loin toutes les entreprises les plus renommées comme Google, Microsoft ou Odoo.",
            "Le fabricant de meuble mondialement reconnu est devenu le sponsor principal des JO. En effet, des épreuves leurs sont dédiées et la moitié des places ne sont réservables qu’avec une carte IKEA family.",
            "Stanley, développeur junior chez Odoo, s’entraîne d’arrache pied pour gagner cette prochaine édition."
    );

    private final List<String> images = List.of(
            "leaderboard_dialog.png",
            ".png",
            ".png"
    );

    @FXML
    public ImageView image;

    private int index = 0;

    @FXML
    void initialize(){
        if(this.paragraph.getText() == null || this.paragraph.getText().isEmpty()){
            this.paragraph.setText(this.texts.get(index));
        }
        index += 1;
    }

    public void handlePaneMouseClicked(MouseEvent mouseEvent) {
        if(index >= this.texts.size()){
            this.pageFlip((Group) Scenes.B_Scene1.getRoot(), Scenes.DragAndDropScene);
            return;
        }
        // If another text is already being displayed, we stop it and display the next one
        if(TextCinematicController.isRunning()){
            TextCinematicController.stop();
            TextCinematicController.setDelayBefore(0);
        }
        else {
            TextCinematicController.setDelayBefore(200);
        }
        TextCinematicController.play(this.texts.get(index), this.paragraph);
        index += 1;
    }
}
