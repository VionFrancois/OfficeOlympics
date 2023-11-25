package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import officeolympics.back.mobels.MobelComponent;
import officeolympics.back.mobels.MobelComponentLocation;
import officeolympics.back.mobels.MobelLayout;

import java.util.ArrayList;
import java.util.List;

public class DragAndDropSceneController extends Controller {
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
    private List<Pane> draggablePanes = new ArrayList<>();
    @FXML
    private List<ImageView> imageViews = new ArrayList<>();
    private List<Double> offsetXList = new ArrayList<>();
    private List<Double> offsetYList = new ArrayList<>();

    private MobelLayout mobelLayout;

    @FXML
    private void initialize() {
        ArrayList<MobelComponent> mobelComponents = new ArrayList<>();
        MobelComponent mobelComponent1 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane1.getLayoutX(), draggablePane1.getLayoutY()),
                new MobelComponentLocation(100, 100));
        mobelComponents.add(mobelComponent1);
        MobelComponent mobelComponent2 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane2.getLayoutX(), draggablePane2.getLayoutY()),
                new MobelComponentLocation(200, 200));
        mobelComponents.add(mobelComponent2);
        MobelComponent mobelComponent3 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane3.getLayoutX(), draggablePane3.getLayoutY()),
                new MobelComponentLocation(300, 300));
        mobelComponents.add(mobelComponent3);
        MobelComponent mobelComponent4 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane4.getLayoutX(), draggablePane4.getLayoutY()),
                new MobelComponentLocation(400, 400));
        mobelComponents.add(mobelComponent4);
        MobelComponent mobelComponent5 = new MobelComponent(0,
                new MobelComponentLocation(draggablePane5.getLayoutX(), draggablePane5.getLayoutY()),
                new MobelComponentLocation(500, 500));
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
            makeDraggable(draggablePanes.get(i), imageViews.get(i), i);
        }
    }

    private void makeDraggable(Pane node, ImageView imageView, int index) {
        node.setOnMousePressed(event -> draggableOnMousePressed(event, node, index));
        node.setOnMouseReleased(event -> draggableOnMouseReleased(event, node, index));
        node.setOnMouseDragged(event -> draggableOnMouseDragged(event, node, index));
        node.setOnDragDetected(event -> draggableOnDragDetected(event, node, imageView));
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
        System.out.print("currentLocation : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).getCurrentLocation());
        System.out.print("targetLocation : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).getTargetLocation());
        System.out.print("isOnTarget : ");
        System.out.println(mobelLayout.getMobelComponents().get(index).isOnTarget());
        System.out.println("=======");
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

        Dragboard dragboard = node.startDragAndDrop(TransferMode.MOVE);

        // Put the image view to the dragboard
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);

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
}
