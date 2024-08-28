package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.bo.SuperBO;
import lk.ijse.aadassignment1backendapi.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveProduct(CustomerDTO customerDTO, Connection connection);

    CustomerDTO getCustomer(String id, Connection connection);

    List<CustomerDTO> getAllCustomers(Connection connection);

    boolean updateCustomer(CustomerDTO customerDTO, Connection connection);
}
