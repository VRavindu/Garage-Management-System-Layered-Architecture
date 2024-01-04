package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
        stage.setScene(scene);
        stage.setTitle("The Wrenchers");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/assets/images/logo.png"));
        stage.show();

    }
}
