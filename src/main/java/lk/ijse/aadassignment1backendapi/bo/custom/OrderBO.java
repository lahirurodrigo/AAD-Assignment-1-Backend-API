package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.bo.SuperBO;
import lk.ijse.aadassignment1backendapi.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDTO orderDto, Connection connection) throws SQLException;
}
