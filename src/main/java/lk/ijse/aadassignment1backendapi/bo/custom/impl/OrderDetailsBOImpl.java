package lk.ijse.aadassignment1backendapi.bo.custom.impl;

import lk.ijse.aadassignment1backendapi.bo.custom.OrderDetailsBO;
import lk.ijse.aadassignment1backendapi.dao.custom.OrderDetailsDAO;
import lk.ijse.aadassignment1backendapi.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.aadassignment1backendapi.dto.OrderDetailsDTO;
import lk.ijse.aadassignment1backendapi.entity.OrderDetails;

import java.sql.Connection;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();

    @Override
    public boolean saveOrderDetails(OrderDetailsDTO dto, Connection connection) {
        return orderDetailsDAO.save(new OrderDetails(
                dto.getOrderId(),
                dto.getCusId(),
                dto.getProId(),
                dto.getQty(),
                dto.getPrice()
        ), connection);
    }
}
