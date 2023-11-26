package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

import javafx.scene.image.ImageView;
import java.util.List;

public class B_Scene1Controller extends Controller {

    @FXML
    public Text paragraph;

    private final List<String> texts = List.of(
            "Sverige har blivit en av de största globala makterna, i synnerhet har IKEA överträffat alla de mest kända företagen som Google, Microsoft eller Odoo.",
            "Den världsberömda möbeltillverkaren har blivit huvudsponsor för de olympiska spelen. Faktum är att evenemang är tillägnade dem och hälften av platserna kan bara bokas med ett IKEA familjekort.",
            "Stanley, juniorutvecklare på Odoo, tränar hårt för att vinna nästa upplaga."
    );

    private final List<String> images = List.of(
            "leaderboard_dialog.png",
            "handshake_dialog.png",
            "employe.png"
    );

    @FXML
    public ImageView image;

    private int index = 0;

    private static final int NUMBER_OF_PAGES = 3;

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

        if(mouseEvent.getButton().toString().equals("MIDDLE")){
            this.pageFlip((Group) Scenes.B_Scene1.getRoot(), Scenes.AlexDoorScene);
            return;
        }

        if(index >= NUMBER_OF_PAGES){
            this.pageFlip((Group) Scenes.B_Scene1.getRoot(), Scenes.AlexDoorScene);
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
