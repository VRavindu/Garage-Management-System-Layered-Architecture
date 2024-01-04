package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
}
