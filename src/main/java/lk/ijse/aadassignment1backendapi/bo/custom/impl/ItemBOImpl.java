package lk.ijse.aadassignment1backendapi.bo.custom.impl;

import lk.ijse.aadassignment1backendapi.bo.custom.ItemBO;
import lk.ijse.aadassignment1backendapi.dao.custom.ItemDAO;
import lk.ijse.aadassignment1backendapi.dao.custom.impl.ItemDAOImpl;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public boolean saveProduct(ItemDTO itemDTO, Connection connection) {
        return itemDAO.save(new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQty()
        ), connection);
    }
}
