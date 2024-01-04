package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpAtndDto {
    private String id;
    private String name;
    private String status;
    private String time;
    private String date;
}
