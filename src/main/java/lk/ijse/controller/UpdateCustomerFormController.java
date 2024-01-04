package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.impl.CustomerBOimpl;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateCustomerFormController {
    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO = new CustomerBOimpl();

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            CustomerDto dto = customerBO.searchCustomer(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillFields(CustomerDto dto) {
        txtId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtTel.setText(dto.getTel());
        txtDate.setText(dto.getDate());
        txtEmpId.setText(dto.getE_id());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidCust = validateCustomer();
        if (isValidCust) {
            String id = txtId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String tel = txtTel.getText();
            String date = txtDate.getText();
            String empId = txtEmpId.getText();

            var dto = new CustomerDto(id, name, address, tel, empId, date);

            try {
                boolean isUpdate = customerBO.updateCustomer(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateCustomer() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(C)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID").show();
            return false;
        }
        String name = txtName.getText();
        boolean nameMatch = Pattern.matches("[A-Za-z]{3,}", name);
        if (!nameMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name").show();
            return false;
        }
        /*String address = txtAddress.getText();
        boolean addressMatch = Pattern.matches("[A-Za-z]", address);
        if (!addressMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Address").show();
            return false;
        }*/
        String tel = txtTel.getText();
        boolean telMatch = Pattern.matches("\\d{10}", tel);
        if (!telMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Telephone Number").show();
            return false;
        }
        /*String empId = txtEmpId.getText();
        boolean empIdMatch = Pattern.matches("[A-Za-z]{4,}", empId);
        if (!empIdMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
            return false;
        }*/
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
        txtEmpId.setText("");
        txtDate.setText("");
    }
}
