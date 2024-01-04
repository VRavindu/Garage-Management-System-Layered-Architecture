package lk.ijse.model;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderModel {
    public static boolean saveOrder(String orderId, String supId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier_order VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, orderId);
        pstm.setString(2, supId);
        pstm.setDate(3, Date.valueOf(date));

        return pstm.executeUpdate() > 0;
    }
}
