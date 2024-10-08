package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO {
    boolean save(Customer customer, Connection connection);

    Customer get(String id, Connection connection);

    List<Customer> getAll(Connection connection);

    boolean update(Customer customer, Connection connection);

    boolean delete(String id, Connection connection);
}
