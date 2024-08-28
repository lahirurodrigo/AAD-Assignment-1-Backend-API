package lk.ijse.aadassignment1backendapi.bo.custom.impl;

import lk.ijse.aadassignment1backendapi.bo.custom.CustomerBO;
import lk.ijse.aadassignment1backendapi.dao.custom.CustomerDAO;
import lk.ijse.aadassignment1backendapi.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.aadassignment1backendapi.dto.CustomerDTO;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import lk.ijse.aadassignment1backendapi.entity.Customer;
import lk.ijse.aadassignment1backendapi.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = new CustomerDAOImpl();
    static Logger logger = LoggerFactory.getLogger(CustomerBOImpl.class);

    @Override
    public boolean saveProduct(CustomerDTO customerDTO, Connection connection) {

        logger.info("Customer id in bo: "+ customerDTO.getTel());

        return customerDAO.save(new Customer(
                customerDTO.getTel(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getEmail()
        ), connection);
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) {
        Customer customer = customerDAO.get(id, connection);
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail()
        );
    }

    @Override
    public List<CustomerDTO> getAllCustomers(Connection connection) {
        List<Customer> customerList =  customerDAO.getAll(connection);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for ( Customer customer : customerList ) {
            customerDTOList.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getEmail()
            ));

        }
        return customerDTOList;
    }
}
