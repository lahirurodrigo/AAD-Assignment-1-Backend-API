package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO {
    boolean save(Order order, Connection connection) throws SQLException;
}
