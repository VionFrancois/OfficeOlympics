package officeolympics.front.controllers;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import officeolympics.front.scenes.Scenes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class is used to control the text cinematic/animation.
 * It is used to display the text in a cinematic way.
 */
public class TextCinematicController extends Controller{

    private static int TEXT_SPEED = 35;

    private static final int TEXT_DELAY = 500;

    private static final int TEXT_DELAY_AFTER = 100;

    private static int TEXT_DELAY_BEFORE = 3000;

    private static final String CLASS_NAME = "text-cinematic";

    private static Thread currentThread;


    /**
     * This method is used to display the text in a cinematic way.
     * @param scene the scene on which the text is and should be displayed
     */
    public static void play(Scene scene) {
        TextCinematicController._playFromStartOn(scene);
    }

    public static void play(String textToDisplay, Text text){
        TextCinematicController._playFromStartOn(textToDisplay, text);
    }

    /**
     * This method is used to display the text in a cinematic way.
     * @param scene the scene on which the text is and should be displayed
     */
    private static void _playFromStartOn(Scene scene) {
        Text text = (Text) scene.lookup("#text-cinematic");
        if (text == null) {
            return;
        }
        String textToDisplay = text.getText();
        _playFromStartOn(textToDisplay, text);
    }

    private static void _playFromStartOn(String textToDisplay, Text text) {
        text.getStyleClass().add(CLASS_NAME);
        TextCinematicController.currentThread = new Thread(() -> {
            text.setText("");
            try {
                Thread.sleep(TEXT_DELAY_BEFORE);
            } catch (InterruptedException e) {
                return;
            }
            for (int i = 0; i < textToDisplay.length(); i++) {
                String finalTextToDisplay = textToDisplay.substring(0, i + 1);
                text.setText(finalTextToDisplay);
                try {
                    if (textToDisplay.charAt(i) == '\n' || textToDisplay.charAt(i) == ',' || textToDisplay.charAt(i) == '.') {
                        Thread.sleep(TEXT_DELAY);
                    } else Thread.sleep(TEXT_SPEED);
                } catch (InterruptedException e) {
                    return;
                }
            }
            try {
                Thread.sleep(TEXT_DELAY_AFTER);
            } catch (InterruptedException e) {
                return;
            }
        });
        TextCinematicController.currentThread.start();
    }

    public static void setDelayBefore(int delay){
        TEXT_DELAY_BEFORE = delay;
    }
    public static void setTextSpeed(int delay){
        TEXT_SPEED = delay;
    }

    public static boolean isRunning() {
        return TextCinematicController.currentThread != null && TextCinematicController.currentThread.isAlive();
    }

    public static void stop() {
        if(isRunning()){
            TextCinematicController.currentThread.interrupt();
        }
    }
}
