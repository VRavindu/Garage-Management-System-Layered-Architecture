package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpAtndTm {
    private String id;
    private String name;
    private String status;
    private String time;
    private String date;
}
