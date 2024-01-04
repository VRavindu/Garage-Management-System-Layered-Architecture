package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RepairBO;
import lk.ijse.dao.custom.RepairDAO;
import lk.ijse.dao.custom.impl.RepairDAOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RepairDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairBOimpl implements RepairBO {
    RepairDAO repairDAO = new RepairDAOimpl();
    @Override
    public boolean saveRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException {
        return repairDAO.save(new Repair(repairDto.getId(), repairDto.getStatus(), repairDto.getCost(), repairDto.getPlateNo(), repairDto.getEmpId(), repairDto.getDate()));
    }

    @Override
    public RepairDto searchRepair(String id) throws SQLException, ClassNotFoundException {
        Repair repair = repairDAO.search(id);
        RepairDto repairDto = new RepairDto(repair.getId(), repair.getStatus(), repair.getCost(), repair.getPlateNo(), repair.getEmpId(), repair.getDate());
        return repairDto;
    }

    @Override
    public boolean updateRepair(RepairDto dto) throws SQLException, ClassNotFoundException {
        return repairDAO.update(new Repair(dto.getId(), dto.getStatus(), dto.getCost(), dto.getPlateNo(), dto.getEmpId(), dto.getDate()));
    }

    @Override
    public List<RepairDto> getAllRepairs() throws SQLException, ClassNotFoundException {
        ArrayList<Repair> repairs = repairDAO.getAll();
        ArrayList<RepairDto> repairDtos = new ArrayList<>();
        for (Repair repair : repairs) {
            repairDtos.add(new RepairDto(repair.getId(), repair.getStatus(),repair.getCost(), repair.getPlateNo(), repair.getEmpId(), repair.getDate()));
        }
        return repairDtos;
    }
}
