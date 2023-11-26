package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

import java.util.List;

public class B_Scene2Controller extends Controller{

    @FXML
    public Text paragraph;

    private final List<String> texts = List.of(
            "Je ne comprends pas... Comment font-ils pour toujours gagner ?"
    );

    private final List<String> images = List.of(
            "leaderboard_end.png"
    );

    @FXML
    public ImageView image;

    private int index = 0;

    private static final int NUMBER_OF_PAGES = 1;

    @FXML
    void initialize(){
        if(this.paragraph.getText() == null || this.paragraph.getText().isEmpty()){
            this.paragraph.setText(this.texts.get(index));
        }
        else if(this.image.getImage() == null){
            this.image.setImage(Scenes.getImage(this.images.get(index)));
        }
        index += 1;
    }

    public void handlePaneMouseClicked(MouseEvent mouseEvent) {
        if(index >= NUMBER_OF_PAGES){
            this.pageFlip((Group) Scenes.B_Scene2.getRoot(), Scenes.AlexDoorScene);
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
        this.image.setImage(Scenes.getImage(this.images.get(index)));
        index += 1;
    }

}
