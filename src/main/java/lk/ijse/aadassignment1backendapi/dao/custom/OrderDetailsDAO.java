package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDetailsDAO {
    boolean save(OrderDetails orderDetails, Connection connection) throws SQLException;
}
