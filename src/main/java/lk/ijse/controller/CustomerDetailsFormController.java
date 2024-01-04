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
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.impl.CustomerBOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerDetailsFormController {

    @FXML
    private AnchorPane P1;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    CustomerBO customerBO = new CustomerBOimpl();

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }
    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDto> dtoList = customerBO.getAllCustomer();

            for (CustomerDto dto : dtoList){
                obList.add(
                        new CustomerTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel(),
                                dto.getE_id(),
                                dto.getDate()
                        )
                );
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("e_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

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
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddCustomerForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UpdateCustomerForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/DeleteCustomerForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void btnVehicleOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/VehicleManageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Vehicle Manage");
        stage.centerOnScreen();
    }
}
