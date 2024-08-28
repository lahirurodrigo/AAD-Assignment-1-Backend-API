package lk.ijse.aadassignment1backendapi.bo.custom.impl;

import lk.ijse.aadassignment1backendapi.bo.custom.OrderBO;
import lk.ijse.aadassignment1backendapi.dao.custom.OrderDAO;
import lk.ijse.aadassignment1backendapi.dao.custom.impl.OrderDAOImpl;
import lk.ijse.aadassignment1backendapi.dto.OrderDTO;
import lk.ijse.aadassignment1backendapi.entity.Order;

import java.sql.Connection;

public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean saveOrder(OrderDTO orderDto, Connection connection) {
        return orderDAO.save(new Order(
                orderDto.getOrderId(),
                orderDto.getCusId(),
                orderDto.getDate()
        ), connection);
    }
}
