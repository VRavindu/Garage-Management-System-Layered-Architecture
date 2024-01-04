package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EmpAtndDto;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(final EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(final EmployeeDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    EmployeeDto searchEmployee(String id) throws SQLException, ClassNotFoundException;
    ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;

    boolean AttendEmp(EmpAtndDto dto) throws SQLException, ClassNotFoundException;

    List<EmpAtndDto> getAllAttendance() throws SQLException, ClassNotFoundException;

    String getWorkingDays(String id, int monthNumber) throws SQLException, ClassNotFoundException;

    String generateNextSalaryId() throws SQLException, ClassNotFoundException;

    boolean saveSalary(EmpSalaryDto dto) throws SQLException, ClassNotFoundException;

    List<EmpSalaryDto> getAllSalary() throws SQLException, ClassNotFoundException;

    XYChart.Series getWorkingDaysChart() throws SQLException, ClassNotFoundException;
}
