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

    private static final int TEXT_SPEED = 50;

    private static final int TEXT_DELAY = 1000;

    private static final int TEXT_DELAY_AFTER = 2000;

    private static final int TEXT_DELAY_BEFORE = 1000;

    private static  final int TEXT_SIZE = 20;

    private final String text;

    private final Runnable callback;

    private Scene scene;

    /**
     * Creates a new TextCinematicController.
     * @param text the text to display
     * @param callback the callback to call when the text cinematic is over
     * @param scene the scene on which the text cinematic should be displayed
     */
    public TextCinematicController(String text, Runnable callback, Scene scene) {
        this.text = text;
        this.callback = callback;
        this.scene = scene;
    }

    /**
     * Starts the text cinematic.
     */
    public void textCinematic() {
        this.textCinematic(0);
    }

    /**
     * Starts the text cinematic.
     * @param index the index of the text to start from
     */
    private void textCinematic(int index) {
        if(index >= this.text.length()) {
            this.callback.run();
            return;
        }
        String textToDisplay = text.split("\n")[index];
        if(!textToDisplay.isEmpty()) {
            this.displayText(textToDisplay);
            this.textCinematic(index + 1);
        }
    }

    /**
     * Displays the given text in a cinematic way on the javafx scene.
     *
     * @param text the text to display
     */
    private void displayText(String text) {
        try {
            Thread.sleep(TEXT_DELAY_BEFORE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Group root = (Group) this.scene.getRoot();
        Text textNode = new Text(text);
        textNode.getStyleClass().add("text-cinematic");
        textNode.setTranslateX(0);
        textNode.setTranslateY(TEXT_SIZE);
        root.getChildren().add(textNode);
        this.scene.getStylesheets().add("css/text-cinematic.css");
        this.scene.setOnKeyPressed(null);
        this.scene.setOnMouseClicked(null);
    }



}
