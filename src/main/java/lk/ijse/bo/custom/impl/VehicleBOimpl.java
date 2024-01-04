package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicleBO;
import lk.ijse.dao.custom.VehicleDAO;
import lk.ijse.dao.custom.impl.VehicleDAOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.VehicleDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOimpl implements VehicleBO {
    VehicleDAO vehicleDAO = new VehicleDAOimpl();
    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(dto.getPlateNo(), dto.getType(), dto.getCustId()));
    }

    @Override
    public boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(dto.getPlateNo(), dto.getType(), dto.getCustId()));
    }

    @Override
    public VehicleDto searchVehicle(String plateNo) throws SQLException, ClassNotFoundException {
        Vehicle vehicle = vehicleDAO.search(plateNo);
        VehicleDto vehicleDto = new VehicleDto(vehicle.getPlateNo(), vehicle.getType(), vehicle.getCustId());
        return vehicleDto;
    }

    @Override
    public List<VehicleDto> getAllVehicles() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> vehicles = vehicleDAO.getAll();
        ArrayList<VehicleDto> vehicleDtos = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDtos.add(new VehicleDto(vehicle.getPlateNo(),vehicle.getType(),vehicle.getCustId()));
        }
        return vehicleDtos;
    }

}
