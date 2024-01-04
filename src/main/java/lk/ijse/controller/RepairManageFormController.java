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
import lk.ijse.bo.custom.RepairBO;
import lk.ijse.bo.custom.impl.RepairBOimpl;
import lk.ijse.dto.RepairDto;
import lk.ijse.dto.tm.RepairTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RepairManageFormController {
    @FXML
    private AnchorPane P1;
    
    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colPtNo;

    @FXML
    private TableColumn<?, ?> colRId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<RepairTm> tblRepair;

    RepairBO repairBO = new RepairBOimpl();

    public void initialize() {
        setCellValueFactory();
        loadAllRepairs();
    }
    private void loadAllRepairs() {

        ObservableList<RepairTm> obList = FXCollections.observableArrayList();

        try {
            List<RepairDto> dtoList = repairBO.getAllRepairs();

            for (RepairDto dto : dtoList){
                obList.add(
                        new RepairTm(
                                dto.getId(),
                                dto.getStatus(),
                                dto.getCost(),
                                dto.getPlateNo(),
                                dto.getEmpId(),
                                dto.getDate()
                        )
                );
            }
            tblRepair.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setCellValueFactory() {
        colRId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colPtNo.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/VehicleManageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Vehicle Manage");
        stage.centerOnScreen();
    }

    public void btnAddRepairOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddRepairForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateRepairOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UpdateRepairForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/RepairManageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Repair Manage");
        stage.centerOnScreen();
    }
}
