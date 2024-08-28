package lk.ijse.aadassignment1backendapi.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aadassignment1backendapi.bo.custom.ItemBO;
import lk.ijse.aadassignment1backendapi.bo.custom.impl.ItemBOImpl;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/item")
public class ItemController extends HttpServlet {

    Connection connection;
    ItemBO itemBO = new ItemBOImpl();

    @Override
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mh-grocery-pos");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO productDto = jsonb.fromJson(req.getReader(), ItemDTO.class);

            boolean isSaved = itemBO.saveProduct(productDto, connection);

                if (isSaved) {
                    writer.println("Saved Successfully");
                }else{
                    writer.println("Saved Failed");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id!=null){
            try(var writer = resp.getWriter()){
                ItemDTO itemDTO = itemBO.getItem(id, connection);
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(itemDTO,writer);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            try(var writer = resp.getWriter()){
                List<ItemDTO> itemDTOList = itemBO.getAllItems(connection);
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(itemDTOList,writer);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);

        try(var writer = resp.getWriter()){

            boolean isUpdated = itemBO.updateProduct(itemDTO, connection);
            if (isUpdated) {
                writer.println("Update Successfully");
            }else{
                writer.println("Update Failed");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
