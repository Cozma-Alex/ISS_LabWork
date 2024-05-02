package javafx.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Buggr extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("/javafx/client/assets/fonts/Roboto/Roboto-Medium.ttf"), 12);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/client/views/login-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        LoginController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setMaximized(true);
        controller.setData(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}