package javafx.client.Utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TaskPriorityTableCell extends TableCell<TaskRowTable, String> {
    private final HBox hbox = new HBox(5);
    private final ImageView imageView = new ImageView();
    private final Label statusLabel = new Label();

    public TaskPriorityTableCell() {
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imageView, statusLabel);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
    }

    @Override
    protected void updateItem(String priority, boolean empty) {
        super.updateItem(priority, empty);
        if (empty || priority == null) {
            setGraphic(null);
        } else {
            statusLabel.setText(priority);
            switch (priority.toLowerCase()) {
                case "high":
                    imageView.setImage(new Image("javafx/client/assets/images/up_arrow.png"));
                    break;
                case "medium":
                    imageView.setImage(new Image("javafx/client/assets/images/right_arrow.png"));
                    break;
                case "low":
                    imageView.setImage(new Image("javafx/client/assets/images/down_arrow.png"));
                    break;
                default:
                    imageView.setImage(null);
                    break;
            }
            setGraphic(hbox);
        }
    }
}

