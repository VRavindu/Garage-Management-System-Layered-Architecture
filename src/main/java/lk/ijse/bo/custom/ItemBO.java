package lk.ijse.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.PlaceOrderTm;
import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(final ItemDto dto) throws SQLException, ClassNotFoundException;
    boolean updateItem(List<PlaceOrderTm> tmList) throws SQLException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    ItemDto searchItem(String code) throws SQLException, ClassNotFoundException;
    List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException;
    boolean updateQty(PlaceOrderTm orderTm) throws SQLException;
    XYChart.Series getItemChart() throws SQLException, ClassNotFoundException;
}
