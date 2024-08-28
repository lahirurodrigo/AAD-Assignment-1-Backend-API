package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.ItemDAO;
import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    SQLUtil sqlUtil = new SQLUtil();

    @Override
    public boolean save(Item item, Connection connection) {
        try{
            return sqlUtil.execute( "INSERT INTO item VALUES(?,?,?,?)",connection,
                    item.getId(),item.getName(),item.getPrice(),item.getQty());

        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Item get(String id, Connection connection) {
        try{
            ResultSet resultSet = sqlUtil.execute("SELECT * FROM item WHERE id = ?",connection,id);

            while(resultSet.next()){
                return new Item(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty")
                );
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getAll(Connection connection) {
        List<Item> items = new ArrayList<>();

        try{
            ResultSet resultSet = sqlUtil.execute("SELECT * FROM item", connection);

            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty")
                ));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public boolean update(Item item, Connection connection) {
        try{
            return sqlUtil.execute( "UPDATE item SET name = ?,price = ?,qty = ? WHERE id = ?", connection,
                    item.getName(),item.getPrice(),item.getQty(),item.getId());
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(String id, Connection connection) {
        try{
            return sqlUtil.execute("DELETE FROM item WHERE id = ?", connection, id);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateItemQty(Item item, Connection connection) {
        try{
            return sqlUtil.execute("UPDATE product SET qty = qty - ? WHERE id = ? ", connection,
                    item.getQty(),item.getId());
        }catch (Exception e){
            return false;
        }
    }
}
