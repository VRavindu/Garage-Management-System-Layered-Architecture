package lk.ijse.bo.custom.impl;

import lk.ijse.bo.SuperBO;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOimpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOimpl();
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getE_id(), dto.getDate()));
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getTel(), customer.getE_id(), customer.getDate());
        return customerDto;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getTel(), dto.getE_id(), dto.getDate()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDto(customer.getId(),customer.getName(),customer.getAddress(), customer.getTel(), customer.getE_id(), customer.getDate()));
        }
        return customerDTOS;
    }
}
