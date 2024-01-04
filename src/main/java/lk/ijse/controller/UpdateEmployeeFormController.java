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
import java.util.regex.Pattern;

public class UpdateEmployeeFormController {
    @FXML
    private JFXButton clear;

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

    @FXML
    private JFXButton update;

    EmployeeBO employeeBO = new EmployeeBOimpl();

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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidEmp = validateEmployee();
        if (isValidEmp) {
            String id = txtId.getText();
            String name = txtName.getText();
            String nic = txtNic.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String email = txtEmail.getText();
            String job = txtJob.getText();
            String date = txtDate.getText();

            var dto = new EmployeeDto(id, name, nic, address, tel, email, job, date);

            try {
                boolean isUpdate = employeeBO.updateEmployee(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateEmployee() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(E)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
            return false;
        }
        String name = txtName.getText();
        boolean nameMatch = Pattern.matches("[A-Za-z]{3,}", name);
        if (!nameMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Name").show();
            return false;
        }
        String nic = txtNic.getText();
        boolean nicMatch = Pattern.matches("[0-9]{9}(v|V)|[0-9]{12}", nic);
        if (!nicMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee NIC").show();
            return false;
        }
        String address = txtAddress.getText();
        boolean addressMatch = Pattern.matches("[A-Za-z]{3,}", address);
        if (!addressMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Address").show();
            return false;
        }
        String tel = txtTel.getText();
        boolean telMatch = Pattern.matches("\\d{10}", tel);
        if (!telMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number").show();
            return false;
        }
        String email = txtEmail.getText();
        boolean emailMatch = Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email);
        if (!emailMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }
        String job = txtJob.getText();
        boolean jobMatch = Pattern.matches("[A-Za-z]{4,}", job);
        if (!jobMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee Job").show();
            return false;
        }
        String date = String.valueOf(txtDate.getText());
        boolean dateMatch = Pattern.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}", date);
        if (!dateMatch) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date").show();
            return false;
        }
        return true;
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
