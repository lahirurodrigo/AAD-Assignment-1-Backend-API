package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.bo.SuperBO;
import lk.ijse.aadassignment1backendapi.dto.CustomerDTO;

import java.sql.Connection;

public interface CustomerBO extends SuperBO {
    boolean saveProduct(CustomerDTO customerDTO, Connection connection);
}
