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
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RegisterFormController {

    @FXML
    private AnchorPane P1;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private Label lblShowCPassword;

    @FXML
    private Label lblShowPassword;

    @FXML
    private ToggleButton showCPass;

    @FXML
    private ToggleButton showPass;

    @FXML
    private PasswordField txtCPassword;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtEmail;

    @FXML
    void btnSignUpOnAction(ActionEvent event) {
        boolean isValidUser = validateUser();
        if (isValidUser) {
            String userName = txtUser.getText();
            String password = txtPassword.getText();
            String cPassword = txtCPassword.getText();
            String email = txtEmail.getText();

            if (password.equals(cPassword)) {
                var dto = new UserDto(userName, cPassword , email);
                var model = new UserModel();

                try {
                    boolean isRegister = model.registerUser(dto);
                    if (isRegister) {
                        new Alert(Alert.AlertType.CONFIRMATION, "User Registered").show();
                        clearFields();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Password not Match").show();
            }
        }
    }

    private boolean validateUser() {
        String userName = txtUser.getText();
        boolean usernameMatch = Pattern.matches("[A-Za-z]{4,}",userName);
        if (!usernameMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid User Name").show();
            return false;
        }
        String password = txtPassword.getText();
        boolean passwordMatch = Pattern.matches("[A-Za-z]{2,}[0-9]{2,}",password);
        if (!passwordMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Password").show();
            return false;
        }
        String cPassword = txtCPassword.getText();
        boolean cPassMatch = Pattern.matches("[A-Za-z]{2,}[0-9]{2,}",cPassword);
        if (!cPassMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Confirm Password").show();
            return false;
        }
        return true;
    }

    private void clearFields() {
        txtUser.setText("");
        txtPassword.setText("");
        txtCPassword.setText("");
    }

    @FXML
    void lblLoginOnAction(MouseEvent event) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("The Wrenchers");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/assets/images/logo.png"));
        stage.show();
    }

    @FXML
    void showCPassOnAction(ActionEvent event) {
        if (showCPass.isSelected()){
            lblShowCPassword.setVisible(true);
            lblShowCPassword.setText(txtCPassword.getText());
        }else {
            lblShowCPassword.setVisible(false);
        }
    }
    @FXML
    void showPassOnAction(ActionEvent event) {
        if (showPass.isSelected()){
            lblShowPassword.setVisible(true);
            lblShowPassword.setText(txtPassword.getText());
        }else {
            lblShowPassword.setVisible(false);
        }
    }
    @FXML
    void txtCPasswordOnAction(ActionEvent event) {

    }
    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtCPassword.requestFocus();
    }
    @FXML
    void txtUserOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
