package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.tm.PlaceOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SupOrderDetailsModel {
    public boolean saveOrderDetail(String orderId, List<PlaceOrderTm> tmList) throws SQLException {
        for (PlaceOrderTm orderTm : tmList) {
            if(!saveOrderDetail(orderId, orderTm)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetail(String orderId, PlaceOrderTm orderTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier_order_details VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, orderTm.getCode());
        pstm.setInt(3, orderTm.getQty());
        pstm.setDouble(4, orderTm.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }
}
