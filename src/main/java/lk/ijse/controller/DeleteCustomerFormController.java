package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.impl.CustomerBOimpl;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.Optional;

import static javafx.scene.control.ButtonType.*;

public class DeleteCustomerFormController {
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO = new CustomerBOimpl();

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        boolean isDeleted = false;
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", YES, NO).showAndWait();
            if (buttonType.orElse(NO)==YES) {
                clearFields();
                isDeleted = customerBO.deleteCustomer(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted Successfully").show();
                }
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
            CustomerDto dto = customerBO.searchCustomer(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer not Found !").show();
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
        txtTel.setText(String.valueOf(dto.getTel()));
        txtDate.setText(dto.getDate());
        txtEmpId.setText(dto.getE_id());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtDate.setText("");
        txtEmpId.setText("");
    }
}
