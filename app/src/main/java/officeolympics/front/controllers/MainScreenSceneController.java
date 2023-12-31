package officeolympics.front.controllers;

import javafx.scene.Group;
import officeolympics.front.navigation.Flow;
import officeolympics.front.navigation.navigators.BackButtonNavigator;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import officeolympics.Main;

/**
 * @author Arnaud MOREAU
 */
public class MainScreenSceneController extends Controller implements BackButtonNavigator {
    @FXML
    Button backButton, transferPermissionButton, newPortfolioButton, requestsStatusButton, accountRemovalButton;

    @Override
    public void handleBackButtonNavigation(MouseEvent event) {
        // Main.setScene(Flow.back());
        // Navigate to the 4 years later scene

        this.pageFlip((Group) Scenes.MainScreenScene.getRoot(), Scenes.YearsLaterScene);
    }

    @Override
    public void emulateBackButtonClicked() {
        handleBackButtonNavigation(null);
    }

    @FXML
    void handleBackButtonClicked(MouseEvent event) {
        handleBackButtonNavigation(event);
    }

    @FXML
    void handleTransferPermissionButtonClicked(MouseEvent event) {
        // Scenes.RequestTransferPermissionScene = SceneLoader.load("RequestTransferPermissionScene.fxml", appLocale);
        // Main.setScene(Flow.forward(Scenes.RequestTransferPermissionScene));
    }

    @FXML
    void handleNewPortfolioButtonClicked(MouseEvent event) {
        // Scenes.RequestNewAccountScene = SceneLoader.load("RequestNewAccountScene.fxml", appLocale);
        // Main.setScene(Flow.forward(Scenes.RequestNewAccountScene));
    }

    @FXML
    void handleRequestsStatusButtonClicked(MouseEvent event) {
        // Scenes.RequestsStatusScene = SceneLoader.load("RequestsStatusScene.fxml", appLocale);
        // Main.setScene(Flow.forward(Scenes.RequestsStatusScene));
    }

    @FXML
    void handleAccountRemovalButtonClicked(MouseEvent mouseEvent) {
        // Scenes.RequestAccountRemovalScene = SceneLoader.load("RequestAccountRemovalScene.fxml", appLocale);
        // Main.setScene(Flow.forward(Scenes.RequestAccountRemovalScene));
    }

    @FXML
    void handleComponentKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            emulateBackButtonClicked();
            event.consume();
        }
    }
}
