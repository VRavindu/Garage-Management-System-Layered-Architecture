package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.impl.ItemBOimpl;
import lk.ijse.dto.ItemDto;

import java.sql.SQLException;

public class DeleteItemFormController {

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtPrice;
    ItemBO itemBO = new ItemBOimpl();

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        boolean isDeleted = false;
        try {
            isDeleted = itemBO.deleteItem(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            ItemDto dto = itemBO.searchItem(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Item not Found !").show();
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
