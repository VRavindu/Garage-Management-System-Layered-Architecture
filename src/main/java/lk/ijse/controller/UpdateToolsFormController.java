package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.bo.custom.ToolBO;
import lk.ijse.bo.custom.impl.ToolBOimpl;
import lk.ijse.dto.ToolDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateToolsFormController {
    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtStatus;

    ToolBO toolBO = new ToolBOimpl();

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String code = txtId.getText();
        try {
            ToolDto toolDto = toolBO.searchTool(code);

            if (toolDto != null){
                fillFields(toolDto);
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isValidTool = validateTool();
        if (isValidTool) {
            String code = txtId.getText();
            String desc = txtDesc.getText();
            String status = txtStatus.getText();

            var dto = new ToolDto(code, desc, status);
            try {
                boolean isUpdate = toolBO.updateTool(dto);

                if (isUpdate) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Tool Updated").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Tool not Updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validateTool() {
        String id = txtId.getText();
        boolean idMatch = Pattern.matches("(T)[0-9]{3,}", id);
        if (!idMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Repair ID").show();
            return false;
        }
        String desc = txtDesc.getText();
        boolean descMatch = Pattern.matches("[A-Za-z]{3,}", desc);
        if (!descMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            return false;
        }
        String status = txtStatus.getText();
        boolean statusMatch = Pattern.matches("[A-Za-z]{3,}", status);
        if (!statusMatch){
            new Alert(Alert.AlertType.ERROR, "Invalid Status").show();
            return false;
        }
        return true;
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
