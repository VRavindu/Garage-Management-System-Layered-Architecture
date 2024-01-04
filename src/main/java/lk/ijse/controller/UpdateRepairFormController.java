package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.RepairBO;
import lk.ijse.bo.custom.impl.RepairBOimpl;
import lk.ijse.dto.RepairDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateRepairFormController {
    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtPlateNo;

    @FXML
    private TextField txtRId;

    @FXML
    private TextField txtStatus;

    RepairBO repairBO = new RepairBOimpl();

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidRepair = validateRepair();
        if (isValidRepair) {
            String id = txtRId.getText();
            String status = txtStatus.getText();
            String cost = txtCost.getText();
            String plateNo = txtPlateNo.getText();
            String empId = txtEmpId.getText();
            String date = txtDate.getText();

            var dto = new RepairDto(id, status, cost, plateNo, empId, date);
            try {
                boolean isUpdate = repairBO.updateRepair(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Repair Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Repair not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtRId.getText();
        try {
            RepairDto dto = repairBO.searchRepair(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Repair not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateRepair() {
        String id = txtRId.getText();
        boolean idMatch = Pattern.matches("(R)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Repair ID").show();
            return false;
        }
        String status = txtStatus.getText();
        boolean statusMatch = Pattern.matches("[A-Za-z]{3,}", status);
        if (!statusMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Status").show();
            return false;
        }
        String cost = txtCost.getText();
        boolean costMatch = Pattern.matches("[0-9]{3,}", cost);
        if (!costMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Cost").show();
            return false;
        }
        String e_id = txtEmpId.getText();
        boolean eIdMatch = Pattern.matches("(E)[0-9]{3,}", e_id);
        if (!eIdMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
            return false;
        }
        String plate = txtPlateNo.getText();
        boolean plateMatch = Pattern.matches("[A-Za-z]{2,}[0-9]{4,}", plate);
        if (!plateMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Plate Number").show();
            return false;
        }
        String date = txtDate.getText();
        boolean dateMatch = Pattern.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}", date);
        if (!dateMatch) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date").show();
            return false;
        }
        return true;
    }

    private void fillFields(RepairDto dto) {
        txtRId.setText(dto.getId());
        txtStatus.setText(dto.getStatus());
        txtCost.setText(dto.getCost());
        txtPlateNo.setText(dto.getPlateNo());
        txtEmpId.setText(dto.getEmpId());
        txtDate.setText(dto.getDate());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtRId.setText("");
        txtStatus.setText("");
        txtCost.setText("");
        txtPlateNo.setText("");
        txtEmpId.setText("");
        txtDate.setText("");
    }
}
