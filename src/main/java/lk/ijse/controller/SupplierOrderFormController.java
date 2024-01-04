package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.bo.custom.impl.ItemBOimpl;
import lk.ijse.bo.custom.impl.SupplierBOimpl;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.dto.SupplierDto;
import lk.ijse.dto.tm.PlaceOrderTm;
import lk.ijse.model.OrderModel;
import lk.ijse.model.PlaceOrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SupplierOrderFormController {
    @FXML
    private AnchorPane P1;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUprice;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TextField txtOid;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQtyH;

    @FXML
    private TableView<PlaceOrderTm> tblSupOrder;

    @FXML
    private TextField txtQty;

    SupplierBO supplierBO = new SupplierBOimpl();
    ItemBO itemBO = new ItemBOimpl();
    private final OrderModel orderModel = new OrderModel();
    private final ObservableList<PlaceOrderTm> obList = FXCollections.observableArrayList();
    private final PlaceOrderModel placeOrderModel = new PlaceOrderModel();



    public void initialize() {
        setCellValueFactory();
        setDate();
        loadSupplierIds();
        loadItemCodes();
    }

    private void loadItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ItemDto> itemList = itemBO.getAllItem();

            for (ItemDto itemDto : itemList) {
                obList.add(itemDto.getItem_code());
            }

            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SupplierDto> supList = supplierBO.getAllSupplier();

            for (SupplierDto dto : supList) {
                obList.add(dto.getId());
            }
            cmbSupplierId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }

    public void cmbCustomerOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = cmbSupplierId.getValue();
        SupplierDto dto = supplierBO.searchSupplier(id);

        lblSupplierName.setText(dto.getName());
    }

    public void cmbItemOnAction(ActionEvent actionEvent) {
        String code = cmbItemCode.getValue();

        txtQty.requestFocus();

        try {
            ItemDto dto = itemBO.searchItem(code);

            lblDesc.setText(dto.getDesc());
            lblPrice.setText(dto.getPrice());
            lblQtyH.setText(dto.getQty());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddtoCartOnAction(ActionEvent actionEvent) {
        boolean isValidOrder = validateOrder();
        if (isValidOrder) {
            String code = cmbItemCode.getValue();
            String description = lblDesc.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double unitPrice = Double.parseDouble(lblPrice.getText());
            double total = qty * unitPrice;
            Button btn = new Button("remove");
            btn.setCursor(Cursor.HAND);

            btn.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you Sure to Remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int index = tblSupOrder.getSelectionModel().getFocusedIndex();
                    obList.remove(index);
                    tblSupOrder.refresh();

                    calculateNetTotal();
                }
            });

            for (int i = 0; i < tblSupOrder.getItems().size(); i++) {
                if (code.equals(colCode.getCellData(i))) {
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblSupOrder.refresh();
                    calculateNetTotal();
                    return;
                }
            }

            obList.add(new PlaceOrderTm(
                    code,
                    description,
                    qty,
                    unitPrice,
                    total,
                    btn
            ));

            tblSupOrder.setItems(obList);
            calculateNetTotal();
            txtQty.clear();
        }
    }

    private boolean validateOrder() {
        String qty = txtQty.getText();
        boolean qtyMatch = Pattern.matches("[0-9]{1,}" , qty);
        if (!qtyMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity").show();
            return false;
        }
        return true;
    }

    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblSupOrder.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }

        lblNetTotal.setText(String.valueOf(total));
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        String orderId = txtOid.getText();
        String supId = cmbSupplierId.getValue();
        LocalDate date = LocalDate.parse(lblDate.getText());

        List<PlaceOrderTm> tmList = new ArrayList<>();

        for (PlaceOrderTm orderTm : obList) {
            tmList.add(orderTm);
        }

        var placeOrderDto = new PlaceOrderDto(
                orderId,
                supId,
                date,
                tmList
        );

        try {
            boolean isSuccess = placeOrderModel.placeOrder(placeOrderDto);

            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order completed!").show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Error").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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

    public void btnSupDetailOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierDetailsForm.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) P1.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Supplier Details");
        stage.centerOnScreen();
    }

    public void btnHistoryOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign load = JRXmlLoader.load(getClass().getResourceAsStream("/report/OrderHistory.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                null,
                DbConnection.getInstance().getConnection()
        );
        JasperViewer.viewReport(jasperPrint,false);
    }

}
