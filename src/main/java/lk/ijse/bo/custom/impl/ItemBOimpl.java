package lk.ijse.bo.custom.impl;

import javafx.scene.chart.XYChart;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.impl.ItemDAOimpl;
import lk.ijse.dto.ItemDto;
import lk.ijse.dto.tm.PlaceOrderTm;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOimpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOimpl();
    @Override
    public boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItem_code(), dto.getQty(), dto.getPrice(), dto.getDesc()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItem_code(), dto.getQty(), dto.getPrice(), dto.getDesc()));
    }

    @Override
    public boolean updateItem(List<PlaceOrderTm> tmList) throws SQLException {
        for (PlaceOrderTm placeOrderTm : tmList) {
            if(!updateQty(placeOrderTm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public ItemDto searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        ItemDto itemDto = new ItemDto(item.getItem_code(), item.getQty(), item.getPrice(), item.getDesc());
        return itemDto;
    }

    @Override
    public List<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items){
            itemDtos.add(new ItemDto(item.getItem_code(), item.getQty(), item.getPrice(), item.getDesc()));
        }
        return itemDtos;
    }

    @Override
    public boolean updateQty(PlaceOrderTm orderTm) throws SQLException {
        return false;
    }

    @Override
    public XYChart.Series getItemChart() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemChart();
    }
}
