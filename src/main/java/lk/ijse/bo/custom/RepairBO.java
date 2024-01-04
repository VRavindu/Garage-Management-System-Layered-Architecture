package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.RepairDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RepairBO extends SuperBO {
    boolean saveRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException;

    RepairDto searchRepair(String id) throws SQLException, ClassNotFoundException;

    boolean updateRepair(RepairDto dto) throws SQLException, ClassNotFoundException;

    List<RepairDto> getAllRepairs() throws SQLException, ClassNotFoundException;
}
