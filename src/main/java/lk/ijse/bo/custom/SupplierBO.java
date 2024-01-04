package lk.ijse.bo.custom;

import lk.ijse.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO {
    boolean saveSupplier(SupplierDto supDto) throws SQLException, ClassNotFoundException;
    SupplierDto searchSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    List<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
}
