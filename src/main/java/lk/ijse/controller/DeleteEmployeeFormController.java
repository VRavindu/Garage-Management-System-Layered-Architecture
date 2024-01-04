package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.impl.EmployeeBOimpl;
import lk.ijse.dto.EmployeeDto;

import java.sql.SQLException;

public class DeleteEmployeeFormController {
    @FXML
    private JFXButton clear;

    @FXML
    private JFXButton delete;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtJob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtTel;

    EmployeeBO employeeBO = new EmployeeBOimpl();

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        boolean isDeleted = false;
        try {
            isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            EmployeeDto dto = employeeBO.searchEmployee(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee not Found !").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillFields(EmployeeDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtNic.setText(dto.getNic());
        txtAddress.setText(dto.getAddress());
        txtTel.setText(dto.getTel());
        txtEmail.setText(dto.getEmail());
        txtJob.setText(dto.getJob());
        txtDate.setText(dto.getDate());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtNic.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtJob.setText("");
        txtDate.setText("");
    }
}
