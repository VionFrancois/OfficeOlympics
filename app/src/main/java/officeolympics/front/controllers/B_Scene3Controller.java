package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

import java.util.List;
import java.util.Map;

public class B_Scene3Controller extends Controller{

    @FXML
    public Text paragraph;

    private final Map<Integer, List<String>> imagesToText = Map.of(
            0, List.of(
                    "Le tiroir est rempli de ces exemplaires à 4 pages !!!"
            ),
            1, List.of(
                    "En rentrant chez lui, Stanley compara leurs manuels et celui qu’on lui a donné pour l’épreuve.",
                    "Pour le même meuble SMÖRBOLL, leurs manuels sont beaucoup moins compliqués.",
                    "*Il a donc 4 ans pour se procurer des manuels suédois des autres meubles afin de les battre à la prochaine édition.*"
            )
    );

    private final List<String> images = List.of(
            "alex_found.png",
            "livrets.png"
    );

    @FXML
    public ImageView image;
    public HBox hbox;

    private int index = 0;
    private int indexText = 0;

    @FXML
    void initialize(){
        if(this.image.getImage() == null){
            this.image.setImage(Scenes.getImage(this.images.get(index)));
        }
        if(this.paragraph.getText() == null || this.paragraph.getText().isEmpty()){
            this.paragraph.setText(this.imagesToText.get(index).get(index));
            indexText += 1;
        }
        this.paragraph.setText("");
    }

    public void handlePaneMouseClicked(MouseEvent mouseEvent) {
        if(index >= images.size() - 1 && indexText >= this.imagesToText.get(index).size()){
            Scenes.YearsLaterScene2 = SceneLoader.load("YearsLaterScene2.fxml");

            this.pageFlip((Group) Scenes.B_Scene3.getRoot(), Scenes.YearsLaterScene2);
            if(TextCinematicController.isRunning()){
                TextCinematicController.stop();
            }
            TextCinematicController.setDelayBefore(1500);
            TextCinematicController.setTextSpeed(100);
            TextCinematicController.play(Scenes.YearsLaterScene2);
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
        if (indexText >= this.imagesToText.get(index).size()) {
            index += 1;
            this.image.setImage(Scenes.getImage(this.images.get(index)));
            indexText = 0;
        }
        TextCinematicController.play(this.imagesToText.get(index).get(indexText), this.paragraph);

        indexText += 1;
    }

}
