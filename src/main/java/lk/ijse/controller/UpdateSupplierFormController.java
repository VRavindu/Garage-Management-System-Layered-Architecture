package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.SupplierBOimpl;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateSupplierFormController {
    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    SupplierBO supplierBO = new SupplierBOimpl();

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            SupplierDto dto = supplierBO.searchSupplier(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillFields(SupplierDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtTel.setText(String.valueOf(dto.getTel()));
        txtEmail.setText(dto.getEmail());
        txtDate.setText(dto.getDate());
        txtEmpId.setText(dto.getE_id());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidSup = validateSupplier();
        if (isValidSup) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String email = txtEmail.getText();
            String date = txtDate.getText();
            String empId = txtEmpId.getText();

            var dto = new SupplierDto(id, name, address, tel, email, empId, date);
            try {
                boolean isUpdate = supplierBO.updateSupplier(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
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
        boolean nameMatch = Pattern.matches("[A-Za-z]{3,}", name);
        if (!nameMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Supplier Name").show();
            return false;
        }
        String address = txtAddress.getText();
        boolean addressMatch = Pattern.matches("[A-Za-z]{3,}", address);
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
        boolean emailMatch = Pattern.matches("[a-zA-Z]{3,}", email);
        if (!emailMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
            return false;
        }
        String e_id = txtEmpId.getText();
        boolean eIdMatch = Pattern.matches("(E)[0-9]{3,}", e_id);
        if (!eIdMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
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
        txtAddress.setText("");
        txtTel.setText("");
        txtEmail.setText("");
        txtEmpId.setText("");
        txtDate.setText("");
    }
}
