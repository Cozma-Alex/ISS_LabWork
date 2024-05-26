package javafx.client.Utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TaskTitleTableCell extends TableCell<TaskRowTable, TaskRowTable> {

    private final HBox hbox = new HBox(5);
    private final Label typeLabel = new Label();
    private final Text titleText = new Text();

    public TaskTitleTableCell() {
        hbox.setAlignment(Pos.CENTER_LEFT);
        typeLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 12));
        hbox.getChildren().addAll(typeLabel, titleText);
    }

    @Override
    protected void updateItem(TaskRowTable task, boolean empty) {
        super.updateItem(task, empty);
        if (empty || task == null) {
            setGraphic(null);
        } else {
            typeLabel.setText(task.getType());
            titleText.setText(task.getTitle());
            switch (task.getType().toLowerCase()) {
                case "bug":
                    typeLabel.setStyle("-fx-background-color: #e75548; -fx-text-fill: white; -fx-padding: 2px 4px; -fx-border-radius: 3px; -fx-background-radius: 3px;");
                    break;
                case "feature":
                    typeLabel.setStyle("-fx-background-color: rgba(51,175,51,0.93); -fx-text-fill: white; -fx-padding: 2px 4px; -fx-border-radius: 3px; -fx-background-radius: 3px;");
                    break;
                default:
                    typeLabel.setStyle("-fx-background-color: #808080; -fx-text-fill: white; -fx-padding: 2px 4px; -fx-border-radius: 3px; -fx-background-radius: 3px;");
                    break;
            }
            setGraphic(hbox);
        }
    }


}
