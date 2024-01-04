package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ToolBO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.dao.custom.impl.ToolDAOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.ToolDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ToolBOimpl implements ToolBO {

    ToolDAO toolDAO = new ToolDAOimpl();
    @Override
    public boolean saveTool(ToolDto dto) throws SQLException, ClassNotFoundException {
        return toolDAO.save(new Tool(dto.getT_code(), dto.getDesc(), dto.getStatus()));
    }

    @Override
    public boolean updateTool(ToolDto dto) throws SQLException, ClassNotFoundException {
        return toolDAO.update(new Tool(dto.getT_code(), dto.getDesc(), dto.getStatus()));
    }

    @Override
    public boolean deleteTool(String code) throws SQLException, ClassNotFoundException {
        return toolDAO.delete(code);
    }

    @Override
    public ToolDto searchTool(String code) throws SQLException, ClassNotFoundException {
        Tool tool = toolDAO.search(code);
        ToolDto toolDto = new ToolDto(tool.getT_code(), tool.getDesc(), tool.getStatus());
        return toolDto;
    }

    @Override
    public List<ToolDto> getAllTools() throws SQLException, ClassNotFoundException {
        ArrayList<Tool> tools = toolDAO.getAll();
        ArrayList<ToolDto> toolDtos=new ArrayList<>();
        for (Tool tool : tools) {
            toolDtos.add(new ToolDto(tool.getT_code(),tool.getDesc(),tool.getStatus()));
        }
        return toolDtos;
    }
}
