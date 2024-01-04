package lk.ijse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dto.EmpAtndDto;
import lk.ijse.dto.EmpSalaryDto;
import lk.ijse.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getId(),entity.getName(),entity.getNic(),entity.getAddress(),entity.getTel(),entity.getEmail(),entity.getJob(),entity.getDate());
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE id = ?",id);
        rst.next();
        return new Employee(id + "", rst.getString("name"), rst.getString("nic"), rst.getString("address"),
                rst.getString("tel"), rst.getString("email"), rst.getString("job"), rst.getString("date"));
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employee SET name = ?, nic = ?, address = ?, tel = ?, email = ?, job = ?, date = ? WHERE id = ?",
                entity.getName(),entity.getNic(),entity.getAddress(),entity.getTel(),entity.getEmail(),entity.getJob(),entity.getDate(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employee WHERE id = ?", id);
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> allEmployee = new ArrayList<>();

        while (rst.next()) {
            Employee entity = new Employee(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("nic"),
                    rst.getString("address"),
                    rst.getString("tel"),
                    rst.getString("email"),
                    rst.getString("job"),
                    rst.getString("date"));
            allEmployee.add(entity);
        }
        return allEmployee;
    }

    @Override
    public boolean AttendEmp(EmpAtndDto entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO attendance VALUES(?, ?, ?, ?, ?)",
                entity.getId(), entity.getName(), entity.getStatus(), entity.getTime(), entity.getDate());
    }

    @Override
    public List<EmpAtndDto> getAllAttendance() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM attendance");
        ArrayList<EmpAtndDto> allAtnd = new ArrayList<>();
        while (rst.next()){
            EmpAtndDto dto = new EmpAtndDto(
                    rst.getString("e_id"),
                    rst.getString("name"),
                    rst.getString("status"),
                    rst.getString("time"),
                    rst.getString("date"));
            allAtnd.add(dto);
        }
        return allAtnd;
    }

    @Override
    public String getWorkingDays(String id, int monthNumber) throws SQLException, ClassNotFoundException {
        ResultSet rst =  SQLUtil.execute("SELECT COUNT(*) FROM attendance WHERE e_id = ? AND MONTH(Date) = ? AND status = 'Present'",
                id,monthNumber);
        String dayCount = null;
        if (rst.next()){
            dayCount = rst.getString(1);
        }
        return dayCount;
    }

    @Override
    public String generateNextSalaryId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT s_id FROM salary ORDER BY s_id DESC LIMIT 1");
        if (rst.next()) {
            int id = Integer.parseInt(rst.getString(1));
            if (id != 0){
                id++;
                return "000" + id;
            }
        }
        return "0001";
    }

    @Override
    public boolean saveSalary(EmpSalaryDto entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary VALUES(?, ?, ?, ?, ?, ?, ?)",
                entity.getS_id(), entity.getDays(), entity.getBonus(), entity.getDSalary(), entity.getTotal(), entity.getDate(), entity.getE_id());
    }

    @Override
    public List<EmpSalaryDto> getAllSalary() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM salary");
        ArrayList<EmpSalaryDto> allSalary = new ArrayList<>();

        while (rst.next()) {
            EmpSalaryDto entity = new EmpSalaryDto(
                    rst.getString("s_id"),
                    rst.getInt("day_count"),
                    rst.getDouble("bonus"),
                    rst.getDouble("daily_salary"),
                    rst.getDouble("amount"),
                    rst.getString("date"),
                    rst.getString("e_id"));
            allSalary.add(entity);
        }
        return allSalary;
    }

    @Override
    public XYChart.Series getWorkingDaysChart() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(status), name FROM attendance WHERE MONTH(Date) AND status = 'Present' GROUP BY name");
        XYChart.Series series = new XYChart.Series();
        while (rst.next()){
            int count = rst.getInt(1);
            String emp = rst.getString(2);

            series.getData().add(new XYChart.Data(emp, count));
        }
        return series;
    }
}
