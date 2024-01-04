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

public class UpdateItemsFormController {

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtPrice;
    ItemBO itemBO = new ItemBOimpl();

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String code = txtId.getText();

        try {
            ItemDto itemDto = itemBO.searchItem(code);

            if (itemDto != null){
                fillFields(itemDto);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillFields(ItemDto dto) {

        txtId.setText(dto.getItem_code());
        txtQty.setText(dto.getQty());
        txtPrice.setText(dto.getPrice());
        txtDesc.setText(dto.getDesc());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidItem = validateItem();
        if (isValidItem) {
            String code = txtId.getText();
            String qty = txtQty.getText();
            String price = txtPrice.getText();
            String desc = txtDesc.getText();

            var dto = new ItemDto(code, qty, price, desc);

            try {
                boolean isUpdate = itemBO.updateItem(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateItem() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(I)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Employee ID").show();
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
        boolean descMatch = Pattern.matches("[A-Za-z]{3,}", desc);
        if (!descMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            return false;
        }
        return true;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        txtDesc.setText("");

    }
}
