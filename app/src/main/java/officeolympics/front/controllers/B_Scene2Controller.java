package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

import java.util.List;
import java.util.Map;

public class B_Scene2Controller extends Controller{

    @FXML
    public Text paragraph;

    private final Map<Integer, List<String>> imagesToText = Map.of(
            0, List.of("Jag förstår inte... Hur vinner de alltid ?"),
            1, List.of(
                    "*När Stanley gick in i omklädningsrumskorridoren såg Stanley en manual ligga nära den svenska dörren.*",
                    "*Han kände igen det direkt, det var uppenbarligen SMÖRBOLL, möbeln han precis byggt till OS.*",
                    "*Men han märkte något speciellt.*"
            ),
            2, List.of(
                    "Men det är bara 4 sidor? Hur är det möjligt ?",
                    "*För att ta reda på det gick Stanley till det svenska omklädningsrummet när alla hade gått.*"
            )
    );

    private final List<String> images = List.of(
            "leaderboard_end.png",
            "smorbol.png",
            "confusion.png"
    );

    @FXML
    public ImageView image;

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
            this.pageFlip((Group) Scenes.B_Scene2.getRoot(), Scenes.DrawersScene);
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
