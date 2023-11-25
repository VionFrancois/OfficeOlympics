package officeolympics.front.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

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
    private List<Pane> draggablePanes = new ArrayList<>();
    @FXML
    private List<ImageView> imageViews = new ArrayList<>();
    private List<Double> offsetXList = new ArrayList<>();
    private List<Double> offsetYList = new ArrayList<>();

    @FXML
    private void initialize() {
        // Add your panes and imageViews to the lists
        draggablePanes.add(draggablePane1);
        draggablePanes.add(draggablePane2);
        draggablePanes.add(draggablePane3);
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);

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
        node.setOnMouseDragged(event -> draggableOnMouseDragged(event, node, index));
        node.setOnDragDetected(event -> draggableOnDragDetected(event, node, imageView));
        node.setOnDragOver(event -> draggableOnDragOver(event, node));
        node.setOnDragDropped(event -> draggableOnDragDropped(event, node, index));
        node.setOnDragDone(DragEvent::consume);
    }

    @FXML
    private void draggableOnMousePressed(MouseEvent event, Node node, int index) {
        offsetXList.set(index, event.getSceneX() - node.getLayoutX());
        offsetYList.set(index, event.getSceneY() - node.getLayoutY());
    }

    @FXML
    private void draggableOnMouseDragged(MouseEvent event, Node node, int index) {
        node.setLayoutX(event.getSceneX() - offsetXList.get(index));
        node.setLayoutY(event.getSceneY() - offsetYList.get(index));
    }

    @FXML
    private void draggableOnDragDetected(MouseEvent event, Node node, ImageView imageView) {
        Dragboard dragboard = node.startDragAndDrop(TransferMode.MOVE);

        // Put the image view to the dragboard
        ClipboardContent content = new ClipboardContent();
        content.putImage(imageView.getImage());
        dragboard.setContent(content);

        event.consume();
    }

    @FXML
    private void draggableOnDragOver(DragEvent event, Node node) {
        if (event.getGestureSource() != node &&
                event.getDragboard().hasImage()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    @FXML
    private void draggableOnDragDropped(DragEvent event, Node node, int index) {
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
}
