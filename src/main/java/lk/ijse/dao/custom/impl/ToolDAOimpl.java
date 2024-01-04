package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ToolDAO;
import lk.ijse.entity.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToolDAOimpl implements ToolDAO {
    @Override
    public boolean save(Tool entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO tools VALUES(?, ?, ?)",
                entity.getT_code(), entity.getDesc(), entity.getStatus());
    }

    @Override
    public Tool search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM tools WHERE t_code = ?",code);
        rst.next();
        return new Tool(code + "", rst.getString("description"), rst.getString("status"));
    }

    @Override
    public boolean update(Tool entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE tools SET description = ?, status = ? WHERE t_code = ?",
                entity.getDesc(), entity.getStatus(), entity.getT_code());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM tools WHERE t_code = ?", code);
    }

    @Override
    public ArrayList<Tool> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM tools");
        ArrayList<Tool> tools = new ArrayList<>();

        while (rst.next()) {
            Tool entity = new Tool(
                    rst.getString("t_code"),
                    rst.getString("description"),
                    rst.getString("status"));
            tools.add(entity);
        }
        return tools;
    }
}
