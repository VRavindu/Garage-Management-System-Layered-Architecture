package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Repair {
    private String id;
    private String status;
    private String cost;
    private String plateNo;
    private String empId;
    private String date;
}
