package javafx.client.Utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TaskStatusTableCell extends TableCell<TaskRowTable, String> {
    private final HBox hbox = new HBox(5);
    private final ImageView imageView = new ImageView();
    private final Label statusLabel = new Label();

    public TaskStatusTableCell() {
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imageView, statusLabel);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
    }

    @Override
    protected void updateItem(String status, boolean empty) {
        super.updateItem(status, empty);
        if (empty || status == null) {
            setGraphic(null);
        } else {
            statusLabel.setText(status);
            switch (status.toLowerCase()) {
                case "todo":
                    imageView.setImage(new Image("javafx/client/assets/images/empty_circle.png"));
                    break;
                case "canceled":
                    imageView.setImage(new Image("javafx/client/assets/images/x_inside_circle.png"));
                    break;
                case "done":
                    imageView.setImage(new Image("javafx/client/assets/images/checkbox_circle.png"));
                    break;
                case "in progress":
                    imageView.setImage(new Image("javafx/client/assets/images/chronometer.png"));
                    break;
                default:
                    imageView.setImage(null);
                    break;
            }
            setGraphic(hbox);
        }
    }
}
