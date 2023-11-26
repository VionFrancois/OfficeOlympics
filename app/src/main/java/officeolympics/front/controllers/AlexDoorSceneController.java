package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import officeolympics.Main;
import officeolympics.back.mobels.MobelComponent;
import officeolympics.back.mobels.MobelComponentLocation;
import officeolympics.back.mobels.MobelLayout;
import officeolympics.front.scenes.SceneLoader;
import officeolympics.front.scenes.Scenes;

import java.util.ArrayList;
import java.util.List;

public class AlexDoorSceneController extends Controller {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    public Pane draggablePane1;
    @FXML
    public ImageView imageView1;
    @FXML
    public Pane draggablePane2;
    @FXML
    public ImageView imageView2;
    @FXML
    public BorderPane draggable_dialog;
    @FXML
    public Text dialog;

    @FXML
    private List<Pane> draggablePanes = new ArrayList<>();
    @FXML
    private List<ImageView> imageViews = new ArrayList<>();

    private List<Double> offsetXList = new ArrayList<>();
    private List<Double> offsetYList = new ArrayList<>();

    private MobelLayout mobelLayout;

    private boolean isDialogOpen = false, endDialog = false, wasDialogOpen = false;
    private int dialogIndex = 0;
    private final List<String> dialogList = List.of(
            "Argh.. C'est n'importe quoi ce manuel, on voit pas le bon côté...",
            "Comment ça se fait que j’arrive pas à dépasser les suédois ? J’ai pourtant fait le meuble en moins de 4 minutes...",
            "Je réessayerai une autre fois..."
    );

    private MobelComponent mobelComponent1, mobelComponent2;

    @FXML
    private void initialize() {
        ArrayList<MobelComponent> mobelComponents = new ArrayList<>();

        mobelComponent1 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane1.getLayoutX(), draggablePane1.getLayoutY()),
                new MobelComponentLocation(690, 250));
        mobelComponents.add(mobelComponent1);
        mobelComponent2 = new MobelComponent(1,
                new MobelComponentLocation(draggablePane2.getLayoutX(), draggablePane2.getLayoutY()),
                new MobelComponentLocation(500, 200));

        mobelLayout = new MobelLayout(mobelComponents);

        // Add your panes and imageViews to the lists
        draggablePanes.add(draggablePane1);
        draggablePanes.add(draggablePane2);

        // Initialize offsets
        for (int i = 0; i < draggablePanes.size(); i++) {
            offsetXList.add(0.0);
            offsetYList.add(0.0);
        }

        // Make all panes draggable
        for (int i = 0; i < draggablePanes.size(); i++) {
            makeDraggable(draggablePanes.get(i), i);
        }
    }

    private void makeDraggable(Pane node, int index) {
        if (index == 1) return; // The base of the furniture is immovable
        System.out.println("make draggable : ");
        System.out.println(node);
        System.out.println(index);
        node.setOnMousePressed(event -> draggableOnMousePressed(event, node, index));
        node.setOnMouseReleased(event -> draggableOnMouseReleased(event, node, index));
        node.setOnMouseDragged(event -> draggableOnMouseDragged(event, node, index));
        // node.setOnDragDetected(event -> draggableOnDragDetected(event, node, imageView));
        node.setOnDragOver(event -> draggableOnDragOver(event, node));
        node.setOnDragDropped(event -> draggableOnDragDropped(event, node, index));
        node.setOnDragDone(DragEvent::consume);
    }

    private void makeUndraggable(Pane node, int index) {
        if (index == 1) return; // The base of the furniture is immovable
        System.out.println("make undraggable : ");
        System.out.println(node);
        System.out.println(index);
        node.setOnMousePressed(event -> {});
        node.setOnMouseReleased(event -> {});
        node.setOnMouseDragged(event -> {});
        // node.setOnDragDetected(event -> {});
        node.setOnDragOver(event -> {});
        node.setOnDragDropped(event -> {});
        node.setOnDragDone(DragEvent::consume);
    }

    @FXML
    private void draggableOnMousePressed(MouseEvent event, Node node, int index) {
        if (!isDialogOpen && !endDialog && !wasDialogOpen) {
            isDialogOpen = true;
            wasDialogOpen = true;
            draggable_dialog.setVisible(true);
            draggable_dialog.setDisable(false);
            draggable_dialog.toFront();
            TextCinematicController.play(dialogList.get(dialogIndex), dialog);
            dialogIndex += 1;
        }

        offsetXList.set(index, event.getSceneX() - node.getLayoutX());
        offsetYList.set(index, event.getSceneY() - node.getLayoutY());
    }

    @FXML
    private void draggableOnMouseReleased(MouseEvent event, Node node, int index) {
        mobelLayout.getMobelComponents().get(index).setCurrentLocation(new MobelComponentLocation(node.getLayoutX(), node.getLayoutY()));
        this.checkIsOnTargetByIndex(index);
        System.out.println(mobelComponent1.isOnTarget());
        if (mobelLayout.isLayoutFilled()) {
            endDialog = true;
            isDialogOpen = true;
            draggable_dialog.setVisible(true);
            draggable_dialog.setDisable(false);
            draggable_dialog.toFront();
            if (TextCinematicController.isRunning()) {
                TextCinematicController.stop();
                TextCinematicController.setDelayBefore(0);
            } else {
                TextCinematicController.setDelayBefore(200);
            }
            TextCinematicController.play(dialogList.get(dialogIndex), dialog);
            dialogIndex += 1;
        }

        System.out.println("Alex porte : " + draggablePane1.getLayoutX() + " " + draggablePane1.getLayoutY());

        // Put the immovable piece to back at all time
        draggablePane2.toBack();
    }

    private void checkIsOnTargetByIndex(int index) {
        if (mobelLayout.getMobelComponents().get(index).isOnTarget()) {

            // Change to glowing image because it's in the right place
            switch (index) {
                case 0 -> {
                    imageView1 = new ImageView(new Image("images/meubles/porte_placard_glow.png")); // glowing image
                    draggablePane1.getChildren().remove(0);
                    draggablePane1.getChildren().add(imageView1);
                    draggablePane1.setLayoutX(mobelComponent1.getTargetLocation().getX());
                    draggablePane1.setLayoutY(mobelComponent1.getTargetLocation().getY());
                    makeUndraggable(draggablePane1, index);
                }
                case 1 -> {
                    imageView2 = new ImageView(new Image("images/meubles/placard_glow.png")); // glowing image
                    draggablePane2.getChildren().remove(0);
                    draggablePane2.getChildren().add(imageView2);
                    draggablePane2.setLayoutX(mobelComponent2.getTargetLocation().getX());
                    draggablePane2.setLayoutY(mobelComponent2.getTargetLocation().getY());
                    makeUndraggable(draggablePane2, index);
                }
                default -> {
                }
            }
        }
    }

    @FXML
    private void draggableOnMouseDragged(MouseEvent event, Node node, int index) {
        printCurrentMethodName();

        node.setLayoutX(event.getSceneX() - offsetXList.get(index));
        node.setLayoutY(event.getSceneY() - offsetYList.get(index));
    }

    @FXML
    private void draggableOnDragDetected(MouseEvent event, Node node, ImageView imageView) {
        printCurrentMethodName();

        event.consume();
    }

    @FXML
    private void draggableOnDragOver(DragEvent event, Node node) {
        printCurrentMethodName();

        if (event.getGestureSource() != node &&
                event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    @FXML
    private void draggableOnDragDropped(DragEvent event, Node node, int index) {
        printCurrentMethodName();

        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasImage()) {
            // Set the new position for the pane
            node.setLayoutX(event.getSceneX() - offsetXList.get(index));
            node.setLayoutY(event.getSceneY() - offsetYList.get(index));
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    public void printCurrentMethodName() {
        String methodName = new Throwable().getStackTrace()[1].getMethodName();
        System.out.println("Current method name: " + methodName);
    }

    public void dialogOnMouseClicked(MouseEvent mouseEvent) {

        if (dialogIndex >= dialogList.size()){
            Scenes.YearsLaterScene = SceneLoader.load("YearsLaterScene.fxml");

            this.pageFlip((Group) Scenes.AlexDoorScene.getRoot(), Scenes.YearsLaterScene);
            TextCinematicController.setDelayBefore(1500);
            TextCinematicController.setTextSpeed(100);
            TextCinematicController.play(Scenes.YearsLaterScene);

            return;
        }

        if (isDialogOpen && !endDialog){
            isDialogOpen = false;
            draggable_dialog.setVisible(false);
            draggable_dialog.setDisable(true);
        }
        else if (isDialogOpen && endDialog){
            if(TextCinematicController.isRunning()){
                TextCinematicController.stop();
                TextCinematicController.setDelayBefore(0);
            }
            else {
                TextCinematicController.setDelayBefore(200);
            }
            TextCinematicController.play(dialogList.get(dialogIndex), dialog);
            dialogIndex += 1;
        }

    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().toString().equals("MIDDLE")){
            Scenes.YearsLaterScene = SceneLoader.load("YearsLaterScene.fxml");

            this.pageFlip((Group) Scenes.AlexDoorScene.getRoot(), Scenes.YearsLaterScene);
            TextCinematicController.setDelayBefore(1500);
            TextCinematicController.setTextSpeed(100);
            TextCinematicController.play(Scenes.YearsLaterScene);
        }
    }
}
