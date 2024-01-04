package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.impl.EmployeeDAOimpl;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.EmpAtndDto;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.dto.EmployeeDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOimpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(dto.getId(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getTel(), dto.getEmail(), dto.getJob(), dto.getDate()));
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getId(), dto.getName(), dto.getNic(), dto.getAddress(), dto.getTel(), dto.getEmail(), dto.getJob(), dto.getDate()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(id);
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(), employee.getName(), employee.getNic(), employee.getAddress(), employee.getTel(), employee.getEmail(), employee.getJob(), employee.getDate());
        return employeeDto;
    }

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getNic(), employee.getAddress(), employee.getTel(), employee.getEmail(), employee.getJob(), employee.getDate()));
        }
        return employeeDtos;
    }

    @Override
    public boolean AttendEmp(EmpAtndDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.AttendEmp(dto);
    }

    @Override
    public List<EmpAtndDto> getAllAttendance() throws SQLException, ClassNotFoundException {
        List<EmpAtndDto> employeeAtnds = employeeDAO.getAllAttendance();
        List<EmpAtndDto> empAtndDtos = new ArrayList<>();
        for (EmpAtndDto empAtndDto : employeeAtnds) {
            empAtndDtos.add(new EmpAtndDto(empAtndDto.getId(), empAtndDto.getName(), empAtndDto.getStatus(), empAtndDto.getTime(), empAtndDto.getDate()));
        }
        return empAtndDtos;
    }

    @Override
    public String getWorkingDays(String id, int monthNumber) throws SQLException, ClassNotFoundException {
        return employeeDAO.getWorkingDays(id, monthNumber);
    }

    @Override
    public String generateNextSalaryId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNextSalaryId();
    }

    @Override
    public boolean saveSalary(EmpSalaryDto dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.saveSalary(dto);
    }

    @Override
    public List<EmpSalaryDto> getAllSalary() throws SQLException, ClassNotFoundException {
        List<EmpSalaryDto> employeeSalary = employeeDAO.getAllSalary();
        List<EmpSalaryDto> empSalaryDtos = new ArrayList<>();
        for (EmpSalaryDto empSalaryDto : employeeSalary) {
            empSalaryDtos.add(new EmpSalaryDto(empSalaryDto.getS_id(), empSalaryDto.getDays(), empSalaryDto.getBonus(), empSalaryDto.getDSalary(), empSalaryDto.getTotal(), empSalaryDto.getDate(), empSalaryDto.getE_id()));
        }
        return empSalaryDtos;
    }

    @Override
    public XYChart.Series getWorkingDaysChart() throws SQLException, ClassNotFoundException {
        return employeeDAO.getWorkingDaysChart();
    }
}
