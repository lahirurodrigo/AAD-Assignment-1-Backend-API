package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.dto.ItemDTO;

import java.sql.Connection;

public interface ItemBO {
    boolean saveProduct(ItemDTO productDto, Connection connection) throws Exception;
}
