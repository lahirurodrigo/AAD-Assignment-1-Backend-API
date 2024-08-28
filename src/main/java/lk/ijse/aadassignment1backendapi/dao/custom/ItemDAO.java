package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDAO {
    boolean save(Item item, Connection connection);

    Item get(String id, Connection connection);

    List<Item> getAll(Connection connection);

    boolean update(Item item, Connection connection);

    boolean delete(String id, Connection connection);
}
