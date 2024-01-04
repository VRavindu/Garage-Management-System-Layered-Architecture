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
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.EmpSalaryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.regex.Pattern;


public class EmployeeSalaryFormController {
    @FXML
    private AnchorPane P1;

    @FXML
    private ComboBox<String> cmbEid;

    @FXML
    private TableColumn<?, ?> colBonus;

    @FXML
    private TableColumn<?, ?> colDays;

    @FXML
    private TableColumn<?, ?> colEid;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblMonth;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSalId;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblWorkDays;

    @FXML
    private Label lblYear;

    @FXML
    private TableView<EmpSalaryTm> tblEmpSalary;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtDSalary;

    EmployeeBO employeeBO = new EmployeeBOimpl();

    public void initialize(){
        loadEmployeeIds();
        generateNextSalaryId();
        setDate();
        setCellValueFactory();
        loadAllSalary();
    }
    private void loadEmployeeIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> empList = employeeBO.getAllEmployees();

            for (EmployeeDto dto : empList){
                obList.add(dto.getId());
            }
            cmbEid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void getWorkingDays(){
        String id = cmbEid.getValue();
        LocalDate date = LocalDate.now();
        int monthNumber = date.getMonthValue();

        try {
            String dayCount = employeeBO.getWorkingDays(id, monthNumber);
            lblWorkDays.setText(dayCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setDate(){
        Month currentMonth = Month.from(java.time.LocalDate.now());
        Year currentYear = Year.now();
        lblYear.setText(String.valueOf(currentYear));
        lblMonth.setText(String.valueOf(currentMonth));

    }

    private void generateNextSalaryId() {
        try {
            String salaryId = employeeBO.generateNextSalaryId();
            lblSalId.setText(salaryId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void cmbEmpIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = cmbEid.getValue();
        EmployeeDto dto = employeeBO.searchEmployee(id);

        lblName.setText(dto.getName());
        getWorkingDays();

    }

    public void btnSalaryOnAction(ActionEvent actionEvent) {
        boolean isValidSalary = validateSalary();
        if (isValidSalary) {

            String sId = lblSalId.getText();
            int dayCount = Integer.parseInt(lblWorkDays.getText());
            double bonus = Double.parseDouble(txtBonus.getText());
            double dSalary = Double.parseDouble(txtDSalary.getText());
            double total = Double.parseDouble(lblTotal.getText());
            String date = String.valueOf(LocalDate.now());
            String eId = cmbEid.getValue();

            var dto = new EmpSalaryDto(sId, dayCount, bonus, dSalary, total, date, eId);

            try {
                boolean isPay = employeeBO.saveSalary(dto);
                if (isPay) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment Added Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateSalary() {
        String bonus = txtBonus.getText();
        boolean bonusMatch = Pattern.matches("[0-9]{1,}", bonus);
        if (!bonusMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Bonus Amount").show();
            return false;
        }
        String dSalary = txtDSalary.getText();
        boolean salaryMatch = Pattern.matches("[0-9]{3,}", dSalary);
        if (!salaryMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Daily Salary Amount").show();
            return false;
        }
        return true;
    }

    public void txtGetTotalOnAction(ActionEvent actionEvent) {
        calculateNetTotal();
    }
    private void calculateNetTotal() {
        int dayCount = Integer.parseInt(lblWorkDays.getText());
        double dSalary = Double.parseDouble(txtDSalary.getText());
        double bonus = Double.parseDouble(txtBonus.getText());

        double total = (dayCount * dSalary) + bonus;
        lblTotal.setText(String.valueOf(total));
    }

    private void setCellValueFactory() {
        colEid.setCellValueFactory(new PropertyValueFactory<>("e_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("dSalary"));
        colBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void loadAllSalary(){
        String empName = lblName.getText();
        ObservableList<EmpSalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<EmpSalaryDto> dtoList = employeeBO.getAllSalary();

            for (EmpSalaryDto dto : dtoList){
                obList.add(
                        new EmpSalaryTm(
                                dto.getE_id(),
                                empName,
                                dto.getDays(),
                                dto.getDSalary(),
                                dto.getBonus(),
                                dto.getTotal()
                        )
                );
            }
            tblEmpSalary.setItems(obList);
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

    public void btnEmpAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeAttendanceForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee Attendance");
        stage.centerOnScreen();
    }

}
