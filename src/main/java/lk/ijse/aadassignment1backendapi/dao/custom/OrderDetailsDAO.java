package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.OrderDetails;

import java.sql.Connection;

public interface OrderDetailsDAO {
    boolean save(OrderDetails orderDetails, Connection connection);
}
