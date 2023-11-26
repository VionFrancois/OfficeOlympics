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
import officeolympics.back.mobels.MobelComponent;
import officeolympics.back.mobels.MobelComponentLocation;
import officeolympics.back.mobels.MobelLayout;
import officeolympics.front.scenes.Scenes;

import java.util.ArrayList;
import java.util.List;

public class TableBuilderSceneController extends Controller {
    @FXML
    public Text dialog;

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Pane draggablePane1;
    @FXML
    private ImageView imageView1;
    @FXML
    private Pane draggablePane2;
    @FXML
    private ImageView imageView2;
    @FXML
    private Pane draggablePane3;
    @FXML
    private ImageView imageView3;
    @FXML
    private Pane draggablePane4;
    @FXML
    private ImageView imageView4;
    @FXML
    private Pane draggablePane5;
    @FXML
    private ImageView imageView5;
    @FXML
    private BorderPane draggable_dialog;

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
                "Kom igen, jag kommer dit! Mer än 2 fot..",
                "Det är allt !"
        );

    @FXML
    private void initialize() {
        ArrayList<MobelComponent> mobelComponents = new ArrayList<>();

        ArrayList<MobelComponentLocation> roulettesTargetLocations = new ArrayList<>();
        roulettesTargetLocations.add(new MobelComponentLocation(271, 179));
        roulettesTargetLocations.add(new MobelComponentLocation(351, 157));
        roulettesTargetLocations.add(new MobelComponentLocation(834, 241));
        roulettesTargetLocations.add(new MobelComponentLocation(895, 213));

        System.out.println(draggablePane1.getLayoutX() + " " + draggablePane1.getLayoutY());
        MobelComponent mobelComponent1 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane1.getLayoutX(), draggablePane1.getLayoutY()),
                new MobelComponentLocation(250, 150));
        mobelComponents.add(mobelComponent1);
        MobelComponent mobelComponent2 = new MobelComponent(1,
                new MobelComponentLocation(draggablePane2.getLayoutX(), draggablePane2.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent2);
        MobelComponent mobelComponent3 = new MobelComponent(2,
                new MobelComponentLocation(draggablePane3.getLayoutX(), draggablePane3.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent3);
        MobelComponent mobelComponent4 = new MobelComponent(3,
                new MobelComponentLocation(draggablePane4.getLayoutX(), draggablePane4.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent4);
        MobelComponent mobelComponent5 = new MobelComponent(4,
                new MobelComponentLocation(draggablePane5.getLayoutX(), draggablePane5.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent5);

        mobelLayout = new MobelLayout(mobelComponents);

        // Add your panes and imageViews to the lists
        draggablePanes.add(draggablePane1);
        draggablePanes.add(draggablePane2);
        draggablePanes.add(draggablePane3);
        draggablePanes.add(draggablePane4);
        draggablePanes.add(draggablePane5);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);

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
        if (index == 0) return; // The base of the chair is immovable
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
        if (index == 0) return; // The base of the chair is immovable
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
        printCurrentMethodName();

        offsetXList.set(index, event.getSceneX() - node.getLayoutX());
        offsetYList.set(index, event.getSceneY() - node.getLayoutY());
    }

    @FXML
    private void draggableOnMouseReleased(MouseEvent event, Node node, int index) {
        printCurrentMethodName();

        mobelLayout.getMobelComponents().get(index).setCurrentLocation(new MobelComponentLocation(node.getLayoutX(), node.getLayoutY()));
        System.out.print("moved index : ");
        System.out.println(index);
        System.out.println(mobelLayout.getMobelComponents().get(index));
        System.out.print("currentLocation : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).getCurrentLocation());
        System.out.print("targetLocation : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).getTargetLocation());
        System.out.print("isOnTarget : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).isOnTarget());
        System.out.println("=======");
        System.out.println(mobelLayout.isLayoutFilled());
        System.out.println("=======");
        checkIsOnTargetByIndex(index);
        if (mobelLayout.isLayoutFilled()) {
            endDialog = true;
            isDialogOpen = true;
            draggable_dialog.setVisible(true);
            draggable_dialog.setDisable(false);
            TextCinematicController.play(dialogList.get(dialogIndex), dialog);
            dialogIndex += 1;
        }
    }

    private void checkIsOnTargetByIndex(int index) {
        if (mobelLayout.getMobelComponents().get(index).isOnTarget()) {

            if(mobelLayout.getMobelComponents().stream().filter(MobelComponent::isOnTarget).count() == 3
                && !wasDialogOpen){
                isDialogOpen = true;
                wasDialogOpen = true;
                draggable_dialog.setVisible(true);
                draggable_dialog.setDisable(false);
                TextCinematicController.play(dialogList.get(dialogIndex), dialog);
                dialogIndex += 1;
            }

            // Change to glowing image because it's in the right place
            switch (index) {
                case 0 -> {
                    imageView1 = new ImageView(new Image("images/meubles/planche_glow.png")); // glowing image
                    draggablePane1.getChildren().remove(0);
                    draggablePane1.getChildren().add(imageView1);
                    makeUndraggable(draggablePane1, index);
                }
                case 1 -> {
                    imageView2 = new ImageView(new Image("images/meubles/pied_glow.png")); // glowing image
                    draggablePane2.getChildren().remove(0);
                    draggablePane2.getChildren().add(imageView2);
                    makeUndraggable(draggablePane2, index);
                }
                case 2 -> {
                    imageView3 = new ImageView(new Image("images/meubles/pied_glow.png")); // glowing image
                    draggablePane3.getChildren().remove(0);
                    draggablePane3.getChildren().add(imageView3);
                    makeUndraggable(draggablePane3, index);
                }
                case 3 -> {
                    imageView4 = new ImageView(new Image("images/meubles/pied_glow.png")); // glowing image
                    draggablePane4.getChildren().remove(0);
                    draggablePane4.getChildren().add(imageView4);
                    makeUndraggable(draggablePane4, index);
                }
                case 4 -> {
                    imageView5 = new ImageView(new Image("images/meubles/pied_glow.png")); // glowing image
                    draggablePane5.getChildren().remove(0);
                    draggablePane5.getChildren().add(imageView5);
                    makeUndraggable(draggablePane5, index);
                }
                default -> {
                }
            }
        }
            /*
            else {
                // Change to non glowing image because it's not in the right place
                switch (index) {
                    case 0 -> {
                        imageView1 = new ImageView(new Image("images/meubles/planche.png"));
                        draggablePane1.getChildren().remove(0);
                        draggablePane1.getChildren().add(imageView1);
                    }
                    case 1 -> {
                        imageView2 = new ImageView(new Image("images/meubles/pied.png"));
                        draggablePane2.getChildren().remove(0);
                        draggablePane2.getChildren().add(imageView2);
                    }
                    case 2 -> {
                        imageView3 = new ImageView(new Image("images/meubles/pied.png"));
                        draggablePane3.getChildren().remove(0);
                        draggablePane3.getChildren().add(imageView3);
                    }
                    case 3 -> {
                        imageView4 = new ImageView(new Image("images/meubles/pied.png"));
                        draggablePane4.getChildren().remove(0);
                        draggablePane4.getChildren().add(imageView4);
                    }
                    case 4 -> {
                        imageView5 = new ImageView(new Image("images/meubles/pied.png"));
                        draggablePane5.getChildren().remove(0);
                        draggablePane5.getChildren().add(imageView5);
                    }
                    default -> {
                    }
                }
            }
             */
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
            this.pageFlip((Group) Scenes.TableBuilderScene.getRoot(), Scenes.B_Scene2);
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
            this.pageFlip((Group) Scenes.TableBuilderScene.getRoot(), Scenes.B_Scene2);
            return;
        }
    }
}

