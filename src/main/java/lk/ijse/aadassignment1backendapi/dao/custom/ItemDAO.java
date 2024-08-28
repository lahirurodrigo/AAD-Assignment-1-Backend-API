package lk.ijse.aadassignment1backendapi.dao.custom;

import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;

public interface ItemDAO {
    boolean save(Item item, Connection connection);
}
