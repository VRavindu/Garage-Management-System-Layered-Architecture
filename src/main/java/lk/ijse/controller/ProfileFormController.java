/*

package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Dto.UserDto;
import lk.ijse.Model.UserModel;
import java.sql.SQLException;

public class ProfileFormController {
    @FXML
    private AnchorPane editPane;

    @FXML
    private Label lblShowPassword;

    @FXML
    private ToggleButton showPass;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPass;

    @FXML
    private TextField txteEmail;

    @FXML
    private TextField txteName;

    @FXML
    private PasswordField txtePass;

    @FXML
    private AnchorPane viewPane;

    private final UserModel userModel = new UserModel();


    public void initialize(){
        getUserDetails();
    }

    private void getUserDetails() {
        UserDto dto = null;
        try {
            dto = userModel.findUserByName(user);
            fillFields(dto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showPassOnAction(ActionEvent event) {
        if (showPass.isSelected()){
            lblShowPassword.setVisible(true);
            lblShowPassword.setText(txtePass.getText());
        }else {
            lblShowPassword.setVisible(false);
        }
    }

    public void btnEditOnAction(ActionEvent actionEvent) {
        viewPane.setVisible(false);
        editPane.setVisible(true);
        getUserDetails();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String nName = txteName.getText();
        String nPass = txtePass.getText();
        String nEmail = txteEmail.getText();

        var dto = new UserDto(nName, nPass, nEmail);
        var model = new UserModel();

        try {
            boolean isUpdate = model.editUser(dto);

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void clearFields() {
        txteName.setText("");
        txtePass.setText("");
        txteEmail.setText("");
    }

    private void fillFields(UserDto dto) {
        txtName.setText(dto.getUser());
        txtPass.setText(dto.getPassword());
        txtEmail.setText(dto.getEmail());
        txteName.setText(dto.getUser());
        txtePass.setText(dto.getPassword());
        txteEmail.setText(dto.getEmail());
    }

    public void btnCancleOnAction(ActionEvent actionEvent) {
        viewPane.setVisible(true);
        editPane.setVisible(false);
    }
}

*/
