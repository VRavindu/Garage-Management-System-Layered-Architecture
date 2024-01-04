package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.EmployeeBOimpl;
import lk.ijse.bo.custom.impl.SupplierBOimpl;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class AddSupplierFormController {
    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private TextField txtAddress;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    EmployeeBO employeeBO = new EmployeeBOimpl();
    SupplierBO supplierBO = new SupplierBOimpl();

    public void initialize(){
        loadEmployeeIds();
    }

    private void loadEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> empList = employeeBO.getAllEmployees();

            for (EmployeeDto dto : empList){
                obList.add(dto.getId());
            }
            cmbEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isValidSup = validateSupplier();
        if (isValidSup) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String email = txtEmail.getText();
            String empId = cmbEmpId.getValue();
            String date = String.valueOf(txtDate.getValue());

            var supDto = new SupplierDto(id, name, address, tel, email, empId, date);
            try {
                boolean isSaved = supplierBO.saveSupplier(supDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Added Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean validateSupplier() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(S)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier ID").show();
            return false;
        }
        String name = txtName.getText();
        boolean nameMatch = Pattern.matches("[A-Za-z ]{3,}", name);
        if (!nameMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name").show();
            return false;
        }
        String address = txtAddress.getText();
        boolean addressMatch = Pattern.matches("[A-Za-z ,]{3,}", address);
        if (!addressMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Address").show();
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
        String date = String.valueOf(txtDate.getValue());
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
        txtAddress.setText("");
        txtTel.setText("");
        txtEmail.setText("");
    }
}
