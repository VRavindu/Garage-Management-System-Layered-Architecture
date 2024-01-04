package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.SupplierDAOimpl;
import lk.ijse.dto.SupplierDto;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOimpl implements SupplierBO {

    SupplierDAO supplierDAO = new SupplierDAOimpl();
    @Override
    public boolean saveSupplier(SupplierDto supDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supDto.getId(), supDto.getName(), supDto.getAddress(), supDto.getTel(), supDto.getEmail(), supDto.getE_id(), supDto.getDate()));
    }

    @Override
    public SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        SupplierDto supplierDto = new SupplierDto(supplier.getId(), supplier.getName(), supplier.getAddress(), supplier.getTel(), supplier.getEmail() ,supplier.getE_id(), supplier.getDate());
        return supplierDto;
    }

    @Override
    public boolean updateSupplier(SupplierDto supDto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supDto.getId(), supDto.getName(), supDto.getAddress(), supDto.getTel(), supDto.getEmail(), supDto.getE_id(), supDto.getDate()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public List<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getId(),supplier.getName(),supplier.getAddress(), supplier.getTel(),supplier.getEmail(), supplier.getE_id(), supplier.getDate()));
        }
        return supplierDtos;
    }
}
