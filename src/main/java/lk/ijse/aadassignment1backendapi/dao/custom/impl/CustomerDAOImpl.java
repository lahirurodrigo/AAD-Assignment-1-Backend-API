package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.controller.CustomerController;
import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.CustomerDAO;
import lk.ijse.aadassignment1backendapi.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class CustomerDAOImpl implements CustomerDAO {

    SQLUtil sqlUtil = new SQLUtil();
    static Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Override
    public boolean save(Customer customer, Connection connection) {
        try{

            logger.info("customer id is: "+customer.getId());

            return sqlUtil.execute( "INSERT INTO customer VALUES(?,?,?,?)",connection,
                    customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail());


        }catch (Exception e){
            logger.debug("The error is"+e.getMessage());
            return false;
        }
    }
}
