package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    boolean save(T dto) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

}
