package officeolympics.front.controllers;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

/**
 * This class is used to control the text cinematic/animation.
 * It is used to display the text in a cinematic way.
 */
public class TextCinematicController extends Controller{

    private static final int TEXT_SPEED = 500;

    private static final int TEXT_DELAY = 1000;

    private static final int TEXT_DELAY_AFTER = 2000;

    private static final int TEXT_DELAY_BEFORE = 1000;

    private static final String CLASS_NAME = "text-cinematic";


    /**
     * This method is used to display the text in a cinematic way.
     * @param scene the scene on which the text is and should be displayed
     */
    public static void play(Scene scene) {
        TextCinematicController._playFromStartOn(scene);
    }

    /**
     * This method is used to display the text in a cinematic way.
     * @param scene the scene on which the text is and should be displayed
     */
    private static void _playFromStartOn(Scene scene) {
        Text text = (Text) scene.lookup("#text-cinematic");
        text.getStyleClass().add(CLASS_NAME);
        scene.lookup("#text-cinematic").setVisible(true);
        String textToDisplay = text.getText();
        text.setText("");
        new Thread(() -> {
            try {
                Thread.sleep(TEXT_DELAY_BEFORE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < textToDisplay.length(); i++) {
                try {
                    Thread.sleep(TEXT_SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String finalTextToDisplay = textToDisplay.substring(0, i + 1);
                text.setText(finalTextToDisplay);
            }
            try {
                Thread.sleep(TEXT_DELAY_AFTER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
