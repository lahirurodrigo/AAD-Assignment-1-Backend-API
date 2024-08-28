package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.OrderDAO;
import lk.ijse.aadassignment1backendapi.entity.Order;

import java.sql.Connection;

public class OrderDAOImpl implements OrderDAO {

    SQLUtil sqlUtil = new SQLUtil();

    @Override
    public boolean save(Order order, Connection connection) {
        try{
            return sqlUtil.execute("INSERT INTO orders VALUES(?,?,?)", connection,
                    order.getOrderId(),order.getCusId(),order.getDate());
        }catch (Exception e){
            return  false;
        }
    }

}
