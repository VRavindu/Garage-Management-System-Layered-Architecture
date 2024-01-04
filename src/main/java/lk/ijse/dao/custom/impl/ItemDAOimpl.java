package lk.ijse.dao.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dto.tm.PlaceOrderTm;
import lk.ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOimpl implements ItemDAO {
    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item_stock VALUES(?, ?, ?, ?)",
                entity.getItem_code(), entity.getQty(), entity.getPrice(), entity.getDesc());
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item_stock WHERE item_code = ?", code);
        rst.next();
        return new Item(code + "", rst.getString("qty"), rst.getString("price"), rst.getString("description"));
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item_stock SET qty = ?, price = ?, description = ? WHERE item_code = ?",
                entity.getQty(), entity.getPrice(), entity.getDesc(), entity.getItem_code());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item_stock WHERE item_code = ?", code);
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item_stock");
        ArrayList<Item> allItem = new ArrayList<>();
        while (rst.next()) {
            Item entity = new Item(
                    rst.getString("item_code"),
                    rst.getString("qty"),
                    rst.getString("price"),
                    rst.getString("description"));
            allItem.add(entity);
        }
        return allItem;
    }

    @Override
    public boolean updateQty(PlaceOrderTm orderTm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item_stock SET qty = qty + ? WHERE item_code = ?", orderTm.getQty(),orderTm.getCode());
    }

    @Override
    public XYChart.Series getItemChart() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT qty , description FROM item_stock");
        XYChart.Series series = new XYChart.Series();
        while (rst.next()){
            int qty = rst.getInt(1);
            String desc = rst.getString(2);

            series.getData().add(new XYChart.Data(desc, qty));
        }
        return series;
    }

    @Override
    public boolean updateItem(List<PlaceOrderTm> tmList) throws SQLException {
        return false;
    }
}
