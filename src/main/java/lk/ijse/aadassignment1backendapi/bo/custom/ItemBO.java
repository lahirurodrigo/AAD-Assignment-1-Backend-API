package lk.ijse.aadassignment1backendapi.bo.custom;

import lk.ijse.aadassignment1backendapi.dto.ItemDTO;

import java.sql.Connection;
import java.util.List;

public interface ItemBO {
    boolean saveProduct(ItemDTO productDto, Connection connection) throws Exception;

    ItemDTO getItem(String id, Connection connection);

    List<ItemDTO> getAllItems(Connection connection);

    boolean updateProduct(ItemDTO itemDTO, Connection connection);
}
