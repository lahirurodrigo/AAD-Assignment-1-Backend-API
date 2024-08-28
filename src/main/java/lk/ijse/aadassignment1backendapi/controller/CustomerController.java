package lk.ijse.aadassignment1backendapi.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aadassignment1backendapi.bo.custom.CustomerBO;
import lk.ijse.aadassignment1backendapi.bo.custom.impl.CustomerBOImpl;
import lk.ijse.aadassignment1backendapi.dto.CustomerDTO;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    Connection connection;
    CustomerBO customerBO = new CustomerBOImpl();
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
            CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            logger.info(customerDTO.toString());

            boolean isSaved = customerBO.saveProduct(customerDTO, connection);

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

        String id = req.getParameter("tel");

        if(id!=null){
            try(var writer = resp.getWriter()){
                CustomerDTO customerDTO = customerBO.getCustomer(id, connection);
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(customerDTO,writer);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else{
            try(var writer = resp.getWriter()){
                List<CustomerDTO> customerDTOList = customerBO.getAllCustomers(connection);
                resp.setContentType("application/json");
                Jsonb jsonb = JsonbBuilder.create();
                jsonb.toJson(customerDTOList,writer);
            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO= jsonb.fromJson(req.getReader(), CustomerDTO.class);

        try(var writer = resp.getWriter()){

            boolean isUpdated = customerBO.updateCustomer(customerDTO, connection);
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
        String id = req.getParameter("tel");

        try(var writer = resp.getWriter()){

            boolean isDelete = customerBO.deleteCustomer(id, connection);

            if (isDelete) {
                writer.println("Delete Successfully");
            }else{
                writer.println("Delete Failed");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
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
