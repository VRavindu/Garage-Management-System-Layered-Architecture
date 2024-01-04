package lk.ijse.dao.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.tm.PlaceOrderTm;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateQty(PlaceOrderTm orderTm) throws SQLException, ClassNotFoundException;
    XYChart.Series getItemChart() throws SQLException, ClassNotFoundException;
    boolean updateItem(List<PlaceOrderTm> tmList) throws SQLException;
}
