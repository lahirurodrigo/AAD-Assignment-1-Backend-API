package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.controller.CustomerController;
import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.CustomerDAO;
import lk.ijse.aadassignment1backendapi.entity.Customer;
import lk.ijse.aadassignment1backendapi.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Customer get(String id, Connection connection) {
        try{
            ResultSet resultSet = sqlUtil.execute("SELECT * FROM customer WHERE id = ?",connection,id);

            while(resultSet.next()){
                return new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email")
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAll(Connection connection) {

        List<Customer> customers = new ArrayList<>();

        try{
            ResultSet resultSet = sqlUtil.execute("SELECT * FROM customer", connection);

            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("email")
                ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return customers;

    }

    @Override
    public boolean update(Customer customer, Connection connection) {
        try{
            return sqlUtil.execute( "UPDATE customer SET name = ?,address = ?,email = ? WHERE id = ?", connection,
                    customer.getName(),customer.getAddress(),customer.getEmail(),customer.getId());
        }catch (Exception e){
            return false;
        }
    }
}
