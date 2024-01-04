package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.custom.impl.EmployeeBOimpl;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.EmployeeTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDetailsFormController {

    @FXML
    private AnchorPane P1;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colDate;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colEmail;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colId;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colJob;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colName;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colNic;

    @FXML
    private javafx.scene.control.TableColumn<?, ?> colTel;

    @FXML
    private TableView<EmployeeTm> tblEmp;

    EmployeeBO employeeBO = new EmployeeBOimpl();


    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colJob.setCellValueFactory(new PropertyValueFactory<>("job"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void loadAllEmployees(){

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = employeeBO.getAllEmployees();

            for (EmployeeDto dto : dtoList){
                obList.add(
                        new EmployeeTm(
                            dto.getId(),
                            dto.getName(),
                            dto.getNic(),
                            dto.getAddress(),
                            dto.getTel(),
                            dto.getEmail(),
                            dto.getJob(),
                            dto.getDate()
                        )
                );
            }
            tblEmp.setItems(obList);
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

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddEmployeeForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UpdateEmployeeForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/DeleteEmployeeForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void btnEmpAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeAttendanceForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee Attendance");
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
}
