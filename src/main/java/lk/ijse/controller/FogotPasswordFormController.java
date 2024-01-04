package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.UserDto;
import lk.ijse.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javax.mail.MessagingException;

public class FogotPasswordFormController {
    @FXML
    private AnchorPane P1;

    @FXML
    private AnchorPane otpPane;

    @FXML
    private AnchorPane resetPassPane;

    @FXML
    private AnchorPane submitPane;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewCPass;

    @FXML
    private PasswordField txtNewPass;

    @FXML
    private TextField txtOtp;

    @FXML
    private TextField txtUsername;

    Random rand = new Random();
    int randomnum;

    public void lblLoginOnAction(MouseEvent mouseEvent) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml")));
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("The Wrenchers");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("/assets/images/logo.png"));
        stage.show();
    }

    public void btnSubmitEmailOnAction(ActionEvent actionEvent) throws SQLException {
        String username = txtUsername.getText();
        String email = txtEmail.getText();

        UserDto dto = UserModel.findUserByName(username);
        if (email.equals(dto.getEmail())){
            randomnum = rand.nextInt(9000);
            randomnum += 1000;

            try {
                submitPane.setVisible(false);
                otpPane.setVisible(true);
                //System.out.println(randomnum);
                OTPEmailController.sendEmail(email, "Test Email", randomnum + "");
                System.out.println("Email sent successfully.");
                clearFields();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email Address").show();
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtEmail.setText("");
    }

    public void btnSubmitOtpOnAction(ActionEvent actionEvent) {
        if(txtOtp.getText().equals(Integer. toString(randomnum))) {
            otpPane.setVisible(false);
            resetPassPane.setVisible(true);
        }else{
            new Alert(Alert.AlertType.ERROR, "Wrong OTP").show();
        }
    }

    public void lblResendOtpOnMouseClicked(MouseEvent mouseEvent) {

    }

    public void btnSubmitPassOnAction(ActionEvent actionEvent) {
        if(txtNewPass.getText().equals(txtNewCPass.getText())) {
            String username = txtUsername.getText();
            String newpassword = txtNewPass.getText();

            try {
                boolean isUpdated = UserModel.update(username, newpassword);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Password Changed").show();
                    resetPassPane.setVisible(false);
                    submitPane.setVisible(true);
                }
            } catch (SQLException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Password not Match").show();
        }
    }
}
