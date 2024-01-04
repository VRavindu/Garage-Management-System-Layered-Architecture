package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.bo.custom.impl.VehicleBOimpl;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateVehicleFormController {
    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtPtNo;

    @FXML
    private TextField txtVType;

    VehicleBO vehicleBO = new VehicleBOimpl();

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String plateNo = txtPtNo.getText();

        try {
            VehicleDto vehicleDto = vehicleBO.searchVehicle(plateNo);

            if (vehicleDto != null){
                fillFields(vehicleDto);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillFields(VehicleDto dto) {

        txtPtNo.setText(dto.getPlateNo());
        txtVType.setText(dto.getType());
        txtCustId.setText(dto.getCustId());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidVehicle = validateVehicle();
        if (isValidVehicle) {
            String plateNo = txtPtNo.getText();
            String type = txtVType.getText();
            String custId = txtCustId.getText();

            var dto = new VehicleDto(plateNo, type, custId);
            try {
                boolean isUpdate = vehicleBO.updateVehicle(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Vehicle not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateVehicle() {
        String type = txtVType.getText();
        boolean typeMatch = Pattern.matches("[A-Za-z]{3,}", type);
        if (!typeMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Type").show();
            return false;
        }
        String id = txtCustId.getText();
        boolean idMatch = Pattern.matches("(C)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID").show();
            return false;
        }
        return true;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtPtNo.setText("");
        txtVType.setText("");
        txtCustId.setText("");

    }
}
