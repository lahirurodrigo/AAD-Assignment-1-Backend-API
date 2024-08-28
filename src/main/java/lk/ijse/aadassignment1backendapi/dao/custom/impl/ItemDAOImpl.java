package lk.ijse.aadassignment1backendapi.dao.custom.impl;

import lk.ijse.aadassignment1backendapi.dao.SQLUtil;
import lk.ijse.aadassignment1backendapi.dao.custom.ItemDAO;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import lk.ijse.aadassignment1backendapi.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
