package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderHistoryTm {
    private String sup_id;
    private String item_code;
    private String order_id;
    private String desc;
    private String qty;
    private String uPrice;
    private String Date;
}
