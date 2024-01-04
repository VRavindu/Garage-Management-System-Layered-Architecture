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
import lk.ijse.bo.custom.RepairBO;
import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.bo.custom.impl.EmployeeBOimpl;
import lk.ijse.bo.custom.impl.RepairBOimpl;
import lk.ijse.bo.custom.impl.VehicleBOimpl;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.RepairDto;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class AddRepairFormController {
    @FXML
    private JFXComboBox<String> cmbEmpId;

    @FXML
    private JFXComboBox<String> cmbPlateNo;

    @FXML
    private TextField txtCost;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtRId;

    @FXML
    private TextField txtStatus;

    EmployeeBO employeeBO = new EmployeeBOimpl();
    RepairBO repairBO = new RepairBOimpl();
    VehicleBO vehicleBO = new VehicleBOimpl();

    public void initialize(){
        loadEmployeeIds();
        loadPlateNo();
    }

    private void loadPlateNo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDto> vehiList = vehicleBO.getAllVehicles();

            for (VehicleDto dto : vehiList){
                obList.add(dto.getPlateNo());
            }
            cmbPlateNo.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

        boolean isValidRepair = validateRepair();
        if (isValidRepair) {
            String rId = txtRId.getText();
            String status = txtStatus.getText();
            String cost = txtCost.getText();
            String empId = cmbEmpId.getValue();
            String plateNo = cmbPlateNo.getValue();
            String date = String.valueOf(txtDate.getValue());

            var repairDto = new RepairDto(rId, status, cost, plateNo, empId, date);
            try {
                boolean isSaved = repairBO.saveRepair(repairDto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Repair Added").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
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
        boolean statusMatch = Pattern.matches("[A-Za-z ]{3,}", status);
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
        txtRId.setText("");
        txtCost.setText("");
        txtStatus.setText("");
    }
}
