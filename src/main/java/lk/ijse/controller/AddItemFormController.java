package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.impl.ItemBOimpl;
import lk.ijse.dto.ItemDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddItemFormController {

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtPrice;

    ItemBO itemBO = new ItemBOimpl();


    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isValidItem = validateItem();
        if (isValidItem) {
            String id = txtId.getText();
            String qty = txtQty.getText();
            String price = txtPrice.getText();
            String desc = txtDesc.getText();

            var dto = new ItemDto(id, qty, price, desc);

            try {
                boolean isSaved = itemBO.saveItem(dto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Added Successfully").show();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateItem() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(I)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Item Code").show();
            return false;
        }
        String qty = txtQty.getText();
        boolean qtyMatch = Pattern.matches("[0-9]{1,}", qty);
        if (!qtyMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity").show();
            return false;
        }
        String price = txtPrice.getText();
        boolean priceMatch = Pattern.matches("[0-9]{1,}", price);
        if (!priceMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Price").show();
            return false;
        }
        String desc = txtDesc.getText();
        boolean descMatch = Pattern.matches("[A-Za-z ]{3,}", desc);
        if (!descMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            return false;
        }
        return true;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFileds();
    }

    private void clearFileds() {
        txtId.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtDesc.setText("");
    }
}
