package lk.ijse.dto;

import lk.ijse.dto.tm.PlaceOrderTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDto {
    private String orderId;
    private String supId;
    private LocalDate date;
    private List<PlaceOrderTm> tmList = new ArrayList<>();
}
