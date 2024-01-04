package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.RepairDAO;
import lk.ijse.entity.Repair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOimpl implements RepairDAO {
    @Override
    public boolean save(Repair entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO repair VALUES(?, ?, ?, ?, ?, ?)",
                entity.getId(), entity.getStatus(), entity.getCost(), entity.getPlateNo(), entity.getEmpId(), entity.getDate());
    }

    @Override
    public Repair search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM repair WHERE r_id = ?", id);
        resultSet.next();
        return new Repair(id + "", resultSet.getString("status"), resultSet.getString("cost"),
                resultSet.getString("plate_no"), resultSet.getString("e_id"), resultSet.getString("date"));
    }

    @Override
    public boolean update(Repair entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE repair SET status = ?, cost = ?, plate_no = ?, e_id = ?, date = ? WHERE r_id = ?",
                entity.getStatus(), entity.getCost(), entity.getPlateNo(), entity.getEmpId(), entity.getDate(), entity.getId());
    }

    @Override
    public ArrayList<Repair> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM repair");
        ArrayList<Repair> repairs = new ArrayList<>();
        while (rst.next()){
            Repair entity = new Repair(
                    rst.getString("r_id"),
                    rst.getString("status"),
                    rst.getString("cost"),
                    rst.getString("plate_no"),
                    rst.getString("e_id"),
                    rst.getString("date"));
            repairs.add(entity);
        }
        return repairs;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
