package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VehicleDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOimpl implements VehicleDAO {
    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehicle VALUES(?, ?, ?)",
                entity.getPlateNo(), entity.getType(), entity.getCustId());
    }

    @Override
    public Vehicle search(String plateNo) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE c_id = ?",plateNo);
        rst.next();
        return new Vehicle(plateNo + "", rst.getString("type"), rst.getString("cust_id"));
    }

    @Override
    public boolean update(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehicle SET type = ?, cust_id = ? WHERE plate_no = ?",
                entity.getType(), entity.getCustId(), entity.getPlateNo());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM vehicle");
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        while (rst.next()) {
            Vehicle entity = new Vehicle(
                    rst.getString("plate_no"),
                    rst.getString("type"),
                    rst.getString("cust_id"));
            vehicles.add(entity);
        }
        return vehicles;
    }
}
