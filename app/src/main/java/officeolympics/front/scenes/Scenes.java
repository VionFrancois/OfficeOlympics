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
            ChairBuilderScene,
            MainScreen2Scene,
            YearsLaterScene,
            YearsLaterScene2,
            EndScreenScene,
            EndScreen2Scene,
            B_Scene1,
            B_Scene2,
            B_Scene3,
            Page1Scene,
            AlexDoorScene,
            TableBuilderScene,
            DrawersScene,
            TheEndScene;

    public static Image getImage(String name) {
        return new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/" + name)));
    }
}
