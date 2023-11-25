package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
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

public class ChairBuilderSceneController extends Controller {
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
    private Pane draggablePane6;
    @FXML
    private ImageView imageView6;
    @FXML
    private Pane draggablePane7;
    @FXML
    private ImageView imageView7;
    @FXML
    private Pane draggablePane8;
    @FXML
    private ImageView imageView8;

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
            "Argh.. C'est n'importe quoi ce manuel...",
            "Comment ça se fait que j’arrive pas à dépasser les suédois ? J’ai pourtant fait le meuble en moins de 4 minutes...",
            "Je réessayerais une autre fois"
    );

    @FXML
    private void initialize() {
        ArrayList<MobelComponent> mobelComponents = new ArrayList<>();

        ArrayList<MobelComponentLocation> roulettesTargetLocations = new ArrayList<>();
        roulettesTargetLocations.add(new MobelComponentLocation(418, 616));
        roulettesTargetLocations.add(new MobelComponentLocation(467, 665));
        roulettesTargetLocations.add(new MobelComponentLocation(719, 666));
        roulettesTargetLocations.add(new MobelComponentLocation(809, 627));

        MobelComponent mobelComponent1 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane1.getLayoutX(), draggablePane1.getLayoutY()),
                new MobelComponentLocation(318, -7));
        mobelComponents.add(mobelComponent1);
        MobelComponent mobelComponent2 = new MobelComponent(1,
                new MobelComponentLocation(draggablePane2.getLayoutX(), draggablePane2.getLayoutY()),
                new MobelComponentLocation(434, 480));
        mobelComponents.add(mobelComponent2);
        MobelComponent mobelComponent3 = new MobelComponent(2,
                new MobelComponentLocation(draggablePane3.getLayoutX(), draggablePane3.getLayoutY()),
                new MobelComponentLocation(411, 196));
        mobelComponents.add(mobelComponent3);
        MobelComponent mobelComponent4 = new MobelComponent(3,
                new MobelComponentLocation(draggablePane4.getLayoutX(), draggablePane4.getLayoutY()),
                new MobelComponentLocation(565, 175));
        mobelComponents.add(mobelComponent4);
        MobelComponent mobelComponent5 = new MobelComponent(4,
                new MobelComponentLocation(draggablePane5.getLayoutX(), draggablePane5.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent5);
        MobelComponent mobelComponent6 = new MobelComponent(5,
                new MobelComponentLocation(draggablePane6.getLayoutX(), draggablePane6.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent6);
        MobelComponent mobelComponent7 = new MobelComponent(6,
                new MobelComponentLocation(draggablePane7.getLayoutX(), draggablePane7.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent7);
        MobelComponent mobelComponent8 = new MobelComponent(7,
                new MobelComponentLocation(draggablePane8.getLayoutX(), draggablePane8.getLayoutY()),
                roulettesTargetLocations);
        mobelComponents.add(mobelComponent8);

        mobelLayout = new MobelLayout(mobelComponents);

        // Add your panes and imageViews to the lists
        draggablePanes.add(draggablePane1);
        draggablePanes.add(draggablePane2);
        draggablePanes.add(draggablePane3);
        draggablePanes.add(draggablePane4);
        draggablePanes.add(draggablePane5);
        draggablePanes.add(draggablePane6);
        draggablePanes.add(draggablePane7);
        draggablePanes.add(draggablePane8);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);
        imageViews.add(imageView5);
        imageViews.add(imageView6);
        imageViews.add(imageView7);
        imageViews.add(imageView8);

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
        if (index == 1) return; // The base of the chair is immovable
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
            draggable_dialog.setVisible(true);
            draggable_dialog.setDisable(false);
            draggable_dialog.toFront();
            TextCinematicController.play(dialogList.get(dialogIndex), dialog);
            dialogIndex += 1;
            endDialog = true;
        }

        // Put the immovable piece to front at all time
        draggablePane2.toFront();
    }

    private void checkIsOnTargetByIndex(int index) {
        if (mobelLayout.getMobelComponents().get(index).isOnTarget()) {

            if (!isDialogOpen && !endDialog && !wasDialogOpen) {
                isDialogOpen = true;
                wasDialogOpen = true;
                draggable_dialog.setVisible(true);
                draggable_dialog.setDisable(false);
                draggable_dialog.toFront();
                TextCinematicController.play(dialogList.get(dialogIndex), dialog);
                dialogIndex += 1;
            }

            // Change to glowing image because it's in the right place
            switch (index) {
                case 0 -> {
                    imageView1 = new ImageView(new Image("images/meubles/chair_glow.png")); // glowing image
                    draggablePane1.getChildren().remove(0);
                    draggablePane1.getChildren().add(imageView1);
                }
                case 1 -> {
                    imageView2 = new ImageView(new Image("images/meubles/chair_base_glow.png")); // glowing image
                    draggablePane2.getChildren().remove(0);
                    draggablePane2.getChildren().add(imageView2);
                }
                case 2 -> {
                    imageView3 = new ImageView(new Image("images/meubles/chair_armrest_l_glow.png")); // glowing image
                    draggablePane3.getChildren().remove(0);
                    draggablePane3.getChildren().add(imageView3);
                }
                case 3 -> {
                    imageView4 = new ImageView(new Image("images/meubles/chair_armrest_r_glow.png")); // glowing image
                    draggablePane4.getChildren().remove(0);
                    draggablePane4.getChildren().add(imageView4);
                }
                case 4 -> {
                    imageView5 = new ImageView(new Image("images/meubles/chair_wheel_glow.png")); // glowing image
                    draggablePane5.getChildren().remove(0);
                    draggablePane5.getChildren().add(imageView5);
                }
                case 5 -> {
                    imageView6 = new ImageView(new Image("images/meubles/chair_wheel_glow.png")); // glowing image
                    draggablePane6.getChildren().remove(0);
                    draggablePane6.getChildren().add(imageView6);
                }
                case 6 -> {
                    imageView7 = new ImageView(new Image("images/meubles/chair_wheel_glow.png")); // glowing image
                    draggablePane7.getChildren().remove(0);
                    draggablePane7.getChildren().add(imageView7);
                }
                case 7 -> {
                    imageView8 = new ImageView(new Image("images/meubles/chair_wheel_glow.png")); // glowing image
                    draggablePane8.getChildren().remove(0);
                    draggablePane8.getChildren().add(imageView8);
                }
                default -> {
                }
            }
        } else {
            // Change to non glowing image because it's not in the right place
            switch (index) {
                case 0 -> {
                    imageView1 = new ImageView(new Image("images/meubles/chair.png"));
                    draggablePane1.getChildren().remove(0);
                    draggablePane1.getChildren().add(imageView1);
                }
                case 1 -> {
                    imageView2 = new ImageView(new Image("images/meubles/chair_base.png"));
                    draggablePane2.getChildren().remove(0);
                    draggablePane2.getChildren().add(imageView2);
                }
                case 2 -> {
                    imageView3 = new ImageView(new Image("images/meubles/chair_armrest_l.png"));
                    draggablePane3.getChildren().remove(0);
                    draggablePane3.getChildren().add(imageView3);
                }
                case 3 -> {
                    imageView4 = new ImageView(new Image("images/meubles/chair_armrest_r.png"));
                    draggablePane4.getChildren().remove(0);
                    draggablePane4.getChildren().add(imageView4);
                }
                case 4 -> {
                    imageView5 = new ImageView(new Image("images/meubles/chair_wheel.png"));
                    draggablePane5.getChildren().remove(0);
                    draggablePane5.getChildren().add(imageView5);
                }
                case 5 -> {
                    imageView6 = new ImageView(new Image("images/meubles/chair_wheel.png"));
                    draggablePane6.getChildren().remove(0);
                    draggablePane6.getChildren().add(imageView6);
                }
                case 6 -> {
                    imageView7 = new ImageView(new Image("images/meubles/chair_wheel.png"));
                    draggablePane7.getChildren().remove(0);
                    draggablePane7.getChildren().add(imageView7);
                }
                case 7 -> {
                    imageView8 = new ImageView(new Image("images/meubles/chair_wheel.png"));
                    draggablePane8.getChildren().remove(0);
                    draggablePane8.getChildren().add(imageView8);
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
            return;
        }

        if (isDialogOpen && !endDialog){
            isDialogOpen = false;
            draggable_dialog.setVisible(false);
            draggable_dialog.setDisable(true);
        }

        else if (isDialogOpen){
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
}
