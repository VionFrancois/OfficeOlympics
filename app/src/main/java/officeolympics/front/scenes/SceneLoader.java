package officeolympics.front.scenes;

import javafx.scene.Group;
import javafx.scene.Parent;
import officeolympics.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Handles the loading of FXML files to create the Scenes.
 *
 * @author Arnaud MOREAU
 */
public class SceneLoader {

    /**
     * Takes the given scene name to find the corresponding FXML file.
     *
     * @param sceneName the name of the scene to load (that is, the name of the FXML file,
     *                  .fxml extension is not mandatory in the string, but it is in the name of the file
     * @return the Scene that was created from the FXML file
     */
    public static Scene load(String sceneName) {
        if (!sceneName.endsWith(".fxml")) sceneName += ".fxml";
        URL url = Main.class.getResource("/xml/scenes/" + sceneName);
        FXMLLoader FXMLLoader = new FXMLLoader(url);
        try {
            Parent sceneParent = FXMLLoader.load();
            Group root = new Group(sceneParent);
            return new Scene(root, 1280, 720);
        } catch (IOException e) {
            System.out.println("FATAL ERROR while trying to load scene " + url);
            e.printStackTrace();
        }
        return null;
    }
}
