package lk.ijse.aadassignment1backendapi.bo.custom.impl;

import lk.ijse.aadassignment1backendapi.bo.custom.ItemBO;
import lk.ijse.aadassignment1backendapi.dao.custom.ItemDAO;
import lk.ijse.aadassignment1backendapi.dao.custom.impl.ItemDAOImpl;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ItemDTO getItem(String id, Connection connection) {
        Item item = itemDAO.get(id, connection);
        return new ItemDTO(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getQty()
        );
    }

    @Override
    public List<ItemDTO> getAllItems(Connection connection) {
        List<Item> itemList =  itemDAO.getAll(connection);
        List<ItemDTO> itemDTOList = new ArrayList<>();

        for ( Item item : itemList) {
            itemDTOList.add(new ItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQty()
            ));

        }
        return itemDTOList;
    }

    @Override
    public boolean updateProduct(ItemDTO itemDTO, Connection connection) {
        return itemDAO.update(new Item(
                itemDTO.getId(),
                itemDTO.getName(),
                itemDTO.getPrice(),
                itemDTO.getQty()
        ), connection);
    }

    @Override
    public boolean deleteItem(String id, Connection connection) {
        return itemDAO.delete(id, connection);
    }
}
