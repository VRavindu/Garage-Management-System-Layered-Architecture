package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ToolDto;
import lk.ijse.entity.Tool;

import java.sql.SQLException;
import java.util.List;

public interface ToolBO extends SuperBO {
    boolean saveTool(ToolDto dto) throws SQLException, ClassNotFoundException;

    boolean updateTool(final ToolDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteTool(String code) throws SQLException, ClassNotFoundException;

    ToolDto searchTool(String code) throws SQLException, ClassNotFoundException;
    List<ToolDto> getAllTools() throws SQLException, ClassNotFoundException;
}
