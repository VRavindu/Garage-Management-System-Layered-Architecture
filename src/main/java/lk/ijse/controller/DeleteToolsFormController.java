package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.ToolBO;
import lk.ijse.bo.custom.impl.ToolBOimpl;
import lk.ijse.dto.ToolDto;

import java.sql.SQLException;

public class DeleteToolsFormController {
    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtStatus;

    ToolBO toolBO = new ToolBOimpl();

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        boolean isDeleted = false;
        try {
            isDeleted = toolBO.deleteTool(id);
            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Tool Deleted Successfully").show();
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
            ToolDto dto = toolBO.searchTool(id);

            if (dto != null){
                fillFields(dto);
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Tool not Found !").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillFields(ToolDto dto) {
        txtId.setText(dto.getT_code());
        txtDesc.setText(dto.getDesc());
        txtStatus.setText(dto.getStatus());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtDesc.setText("");
        txtStatus.setText("");

    }
}
