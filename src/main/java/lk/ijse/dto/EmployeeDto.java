package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String tel;
    private String email;
    private String job;
    private String date;
}
