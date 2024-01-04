package lk.ijse.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.EmpAtndDto;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee> {
    boolean AttendEmp(EmpAtndDto dto) throws SQLException, ClassNotFoundException;

    List<EmpAtndDto> getAllAttendance() throws SQLException, ClassNotFoundException;

    String getWorkingDays(String id, int monthNumber) throws SQLException, ClassNotFoundException;

    String generateNextSalaryId() throws SQLException, ClassNotFoundException;

    boolean saveSalary(EmpSalaryDto dto) throws SQLException, ClassNotFoundException;

    List<EmpSalaryDto> getAllSalary() throws SQLException, ClassNotFoundException;

    XYChart.Series getWorkingDaysChart() throws SQLException, ClassNotFoundException;
}
