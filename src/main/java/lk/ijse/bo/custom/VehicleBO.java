package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.VehicleDto;

import java.sql.SQLException;
import java.util.List;

public interface VehicleBO extends SuperBO {
    boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;

    boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;

    VehicleDto searchVehicle(String plateNo) throws SQLException, ClassNotFoundException;

    List<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException;
}
