package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String tel;
    private String email;
    private String job;
    private String date;
}
