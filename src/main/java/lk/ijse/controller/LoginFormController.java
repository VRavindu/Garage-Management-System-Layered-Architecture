package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane P1;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblShowPassword;

    @FXML
    private ToggleButton showPass;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;


    @FXML
    void showPassOnAction(ActionEvent event) {
        if (showPass.isSelected()){
            lblShowPassword.setVisible(true);
            lblShowPassword.setText(txtPassword.getText());
        }else {
            lblShowPassword.setVisible(false);
        }
    }
    public void txtUserOnAction(ActionEvent actionEvent) throws IOException {
            txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        btnLoginOnAction(actionEvent);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Main Dashboard");
        stage.getIcons().add(new Image("/assets/images/logo2.png"));
        stage.centerOnScreen();
    }

    public void lblSignUpOnAction(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.getIcons().add(new Image("/assets/images/logo2.png"));
        stage.centerOnScreen();
    }

    public void lblFogotPassOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/FogotPasswordForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.getIcons().add(new Image("/assets/images/logo2.png"));
        stage.centerOnScreen();
    }



    /*public void btnLoginOnAction(ActionEvent actionEvent) {
        String user = txtUser.getText();
        String password = txtPassword.getText();

        var model = new UserModel();

        try {
            ResultSet resultSet = model.userLogin(user, password);

            if (resultSet.next()){
                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/DashboardForm.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) P1.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Main Dashboard");
                stage.getIcons().add(new Image("/assets/images/logo2.png"));
                stage.centerOnScreen();

            }else {
                new Alert(Alert.AlertType.ERROR, "LOGIN FAILED").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
