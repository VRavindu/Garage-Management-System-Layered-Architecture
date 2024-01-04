package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PlaceOrderTm {
    private String code;
    private String desc;
    private int qty;
    private double unitPrice;
    private double total;
    private Button btn;
}
