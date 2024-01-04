package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.impl.EmployeeBOimpl;
import lk.ijse.dto.EmpAtndDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.EmpAtndTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeAttendanceFormController {
    EmployeeBO employeeBO = new EmployeeBOimpl();
    @FXML
    private ComboBox cmbEmpId;

    @FXML
    private AnchorPane P1;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEId;

    @FXML
    private TableColumn<?, ?> colEName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStatus;

    @FXML
    private RadioButton rBtnAbs;

    @FXML
    private RadioButton rBtnPre;

    @FXML
    private ToggleGroup status;

    @FXML
    private TableView<EmpAtndTm> tblAttend;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtTime;

    public void initialize() {
        setCellValueFactory();
        loadAllAttendance();
        loadEmployeeIds();
    }

    private void setCellValueFactory() {
        colEId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

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

    public void cmbEmpIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = (String) cmbEmpId.getValue();
        EmployeeDto dto = employeeBO.searchEmployee(id);

        lblName.setText(dto.getName());

    }
    private void loadAllAttendance(){
        ObservableList<EmpAtndTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpAtndDto> dtoList = employeeBO.getAllAttendance();

            for (EmpAtndDto dto : dtoList){
                obList.add(
                        new EmpAtndTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getStatus(),
                                dto.getTime(),
                                dto.getDate()
                        )
                );
            }
            tblAttend.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Main Dashboard");
        stage.centerOnScreen();
    }

    public void btnEmpDetailOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeDetailsForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee Details");
        stage.centerOnScreen();
    }

    public void btnEmpSalaryOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeSalaryForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee Salary");
        stage.centerOnScreen();
    }

    public void btnAttendOnAction(ActionEvent actionEvent) {
        boolean isValidAttendance = validateAttendance();
        if (isValidAttendance) {
            String id = (String) cmbEmpId.getValue();
            String name = lblName.getText();
            String status = lblStatus.getText();
            String time = txtTime.getText();
            String date = String.valueOf(txtDate.getValue());

            var dto = new EmpAtndDto(id, name, status, time, date);
            try {
                boolean isAtnd = employeeBO.AttendEmp(dto);

                if (isAtnd) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Attendance Successfully").show();
                    tblAttend.refresh();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateAttendance() {
        String time = txtTime.getText();
        boolean timeMatch = Pattern.matches("[0-9]{2}\\.[0-9]{2}", time);
        if (!timeMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Time Format").show();
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

    public void rBtnStatusOnAction(ActionEvent actionEvent) {
        if (rBtnAbs.isSelected()){
            lblStatus.setText("Absent");
        }else if (rBtnPre.isSelected()){
            lblStatus.setText("Present");
        }
    }

}
