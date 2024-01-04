package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getAddress(),entity.getTel(),entity.getE_id(),entity.getDate());
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE c_id = ?",id);
        rst.next();
        return new Customer(id + "", rst.getString("c_name"), rst.getString("c_address"),
        rst.getString("c_contact"), rst.getString("e_id"), rst.getString("date"));
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET c_name = ?, c_address = ?, c_contact = ?, e_id = ?, date = ? WHERE c_id = ?",
                entity.getName(), entity.getAddress(), entity.getTel(),entity.getE_id(),entity.getDate(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE c_id = ?", id);
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(
                    rst.getString("c_id"),
                    rst.getString("c_name"),
                    rst.getString("c_address"),
                    rst.getString("c_contact"),
                    rst.getString("e_id"),
                    rst.getString("date"));
            allCustomer.add(entity);
        }
        return allCustomer;
    }
}
