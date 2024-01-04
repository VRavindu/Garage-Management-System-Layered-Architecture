package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?, ?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getTel(), entity.getEmail(), entity.getE_id(), entity.getDate());
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE sup_id = ?",id);
        rst.next();
        return new Supplier(id + "", rst.getString("name"), rst.getString("address"),
                rst.getString("contact"),rst.getString("email"), rst.getString("e_id"), rst.getString("date"));
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET name = ?, address = ?, contact = ?, email = ?, e_id = ?, date = ? WHERE sup_id = ?",
                entity.getName(), entity.getAddress(), entity.getTel(), entity.getEmail(), entity.getE_id(), entity.getDate(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE sup_id = ?", id);
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();

        while (rst.next()) {
            Supplier entity = new Supplier(
                    rst.getString("sup_id"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("contact"),
                    rst.getString("email"),
                    rst.getString("e_id"),
                    rst.getString("date"));
            suppliers.add(entity);
        }
        return suppliers;
    }
}
