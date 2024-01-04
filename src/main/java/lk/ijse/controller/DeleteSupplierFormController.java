package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.SupplierBOimpl;
import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;

public class DeleteSupplierFormController {
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmpId;

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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        boolean isDeleted = false;
        try {
            isDeleted = supplierBO.deleteSupplier(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
