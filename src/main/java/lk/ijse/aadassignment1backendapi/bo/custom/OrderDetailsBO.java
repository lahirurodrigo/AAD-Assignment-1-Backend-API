package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.bo.SuperBO;
import lk.ijse.aadassignment1backendapi.dto.OrderDetailsDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDetailsBO extends SuperBO {
    boolean saveOrderDetails(OrderDetailsDTO dto, Connection connection) throws SQLException;
}
