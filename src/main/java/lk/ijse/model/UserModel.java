package lk.ijse.model;

import lk.ijse.db.DbConnection;
import lk.ijse.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static UserDto findUserByName(String username) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new UserDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    public static boolean update(String username, String newpassword) throws SQLException {
        String sql = "UPDATE user SET password =? WHERE username = ?";
        try (PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, newpassword);
            pstm.setString(2,username);

            return pstm.executeUpdate() > 0;
        }
    }

    public ResultSet userLogin(String user, String pass) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM user WHERE userName = ? and password = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, user);
        pstm.setString(2, pass);

        ResultSet resultSet = pstm.executeQuery();
        return resultSet;
    }

    public boolean registerUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String  sql = "INSERT INTO user VALUES (? , ? , ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUser());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        return pstm.executeUpdate() > 0;

    }

    public boolean editUser(final UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE user SET userName = ?, password = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUser());
        pstm.setString(2, dto.getPassword());

        boolean isUpdate = pstm.executeUpdate() > 0;
        return isUpdate;
    }
}
