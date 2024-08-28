package lk.ijse.aadassignment1backendapi.dao;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {

    public <T> T execute(String sql, Connection connection, Object...objects) throws SQLException, NamingException, SQLException {
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < objects.length; i++) {
            pstm.setObject(i+1,objects[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else {
            return (T) (Boolean) (pstm.executeUpdate()>0);
        }
    }
}
