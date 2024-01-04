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
import lk.ijse.bo.custom.ToolBO;
import lk.ijse.bo.custom.impl.ToolBOimpl;
import lk.ijse.dto.ToolDto;
import lk.ijse.dto.tm.ToolTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ToolManageFormController {

    @FXML
    private AnchorPane P1;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTId;

    @FXML
    private TableView<ToolTm> tblTool;

    ToolBO toolBO = new ToolBOimpl();

    public void initialize() {
        setCellValueFactory();
        loadAllTools();
    }

    private void setCellValueFactory() {
        colTId.setCellValueFactory(new PropertyValueFactory<>("t_code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllTools() {
        ObservableList<ToolTm> obList = FXCollections.observableArrayList();

        try {
            List<ToolDto> dtoList = toolBO.getAllTools();

            for (ToolDto dto : dtoList){
                obList.add(
                        new ToolTm(
                                dto.getT_code(),
                                dto.getDesc(),
                                dto.getStatus()
                        )
                );
            }
            tblTool.setItems(obList);
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

    public void btnItemStockOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ItemStockManageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Material Manage");
        stage.centerOnScreen();
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddToolsForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UpdateToolsForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/DeleteToolsForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
}
