package officeolympics.front.scenes;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import officeolympics.Main;

import java.util.Objects;

/**
 * Holds every required Scene for the Application.
 *
 * @author Arnaud MOREAU
 */
public class Scenes {
    public static Scene
            MainScreenScene,
            DragAndDropScene,
            MainScreen2Scene,
            YearsLaterScene,
            EndScreenScene,
            B_Scene1,
            Page1Scene;

    public static Image getImage(String name) {
        return new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/" + name)));
    }
}
