package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpSalaryTm {
    private String e_id;
    private String name;
    private int days;
    private double dSalary;
    private double bonus;
    private double total;
}
