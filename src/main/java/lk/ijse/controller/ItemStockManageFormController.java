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
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.impl.ItemBOimpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.ItemTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ItemStockManageFormController {

    @FXML
    private AnchorPane P1;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItmCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<ItemTm> tblItmStk;
    ItemBO itemBO = new ItemBOimpl();

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
    }

    private void setCellValueFactory() {
        colItmCode.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDto> dtoList = itemBO.getAllItem();

            for (ItemDto dto : dtoList){
                obList.add(
                        new ItemTm(
                                dto.getItem_code(),
                                dto.getQty(),
                                dto.getPrice(),
                                dto.getDesc()
                        )
                );
            }
            tblItmStk.setItems(obList);
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
    public void btnToolOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ToolManageForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Tool Details");
        stage.centerOnScreen();
    }
    public void btnAddOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddItemForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/UpdateItemForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =  new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/DeleteItemForm.fxml"));
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void btnReportOnAction(ActionEvent actionEvent) throws SQLException, JRException {
        JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("/report/ItemStock.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint,false);
    }
}
