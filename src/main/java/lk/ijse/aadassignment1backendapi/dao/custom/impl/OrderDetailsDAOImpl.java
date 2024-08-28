package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.OrderDetailsDAO;
import lk.ijse.aadassignment1backendapi.entity.OrderDetails;

import java.sql.Connection;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    SQLUtil sqlUtil = new SQLUtil();

    @Override
    public boolean save(OrderDetails orderDetails, Connection connection) {
        try {
            return sqlUtil.execute("INSERT INTO orderDetails VALUES(?,?,?,?)", connection,
                    orderDetails.getOrderId(),orderDetails.getProId(),orderDetails.getQty(),orderDetails.getPrice());
        }catch (Exception e){
            return false;
        }
    }
}
