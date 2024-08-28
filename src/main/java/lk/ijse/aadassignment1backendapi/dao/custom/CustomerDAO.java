package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.Customer;

import java.sql.Connection;

public interface CustomerDAO {
    boolean save(Customer customer, Connection connection);
}
