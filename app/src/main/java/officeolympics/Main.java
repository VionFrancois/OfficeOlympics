/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package officeolympics;

import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import officeolympics.front.controllers.TextCinematicController;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

public class Main extends Application {
    private static Stage stage;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static Stage getStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage_) {
        stage = stage_;

        Scenes.MainScreenScene = SceneLoader.load("MainScreenScene.fxml");
        Scenes.AlexDoorScene = SceneLoader.load("AlexDoorScene.fxml");
        Scenes.ChairBuilderScene = SceneLoader.load("ChairBuilderScene.fxml");
        Scenes.TableBuilderScene = SceneLoader.load("TableBuilderScene.fxml");
        Scenes.MainScreen2Scene = SceneLoader.load("MainScreen2Scene.fxml");
        //Scenes.EndScreenScene = SceneLoader.load("EndScreenScene.fxml");
        Scenes.Page1Scene = SceneLoader.load("Page1.fxml");
        Scenes.B_Scene1 = SceneLoader.load("B_Scene1.fxml");
        Scenes.B_Scene2 = SceneLoader.load("B_Scene2.fxml");
        TextCinematicController.play(Scenes.B_Scene1);

        stage.setResizable(true);
        stage.setTitle("FUMA");
        stage.setScene(Scenes.MainScreen2Scene);
        stage.show();
    }

    public static void setScene(Scene scene) {
        stage.setScene(scene);
    }
}
