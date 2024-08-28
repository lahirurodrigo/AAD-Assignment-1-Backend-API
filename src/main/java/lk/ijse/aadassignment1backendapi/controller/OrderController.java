package lk.ijse.aadassignment1backendapi.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.aadassignment1backendapi.bo.custom.ItemBO;
import lk.ijse.aadassignment1backendapi.bo.custom.OrderBO;
import lk.ijse.aadassignment1backendapi.bo.custom.OrderDetailsBO;
import lk.ijse.aadassignment1backendapi.bo.custom.impl.ItemBOImpl;
import lk.ijse.aadassignment1backendapi.bo.custom.impl.OrderBOImpl;
import lk.ijse.aadassignment1backendapi.bo.custom.impl.OrderDetailsBOImpl;
import lk.ijse.aadassignment1backendapi.dto.ItemDTO;
import lk.ijse.aadassignment1backendapi.dto.OrderDTO;
import lk.ijse.aadassignment1backendapi.dto.OrderDetailsDTO;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/order")
public class OrderController extends HttpServlet {

    Connection connection;
    OrderBO orderBo = new OrderBOImpl();
    ItemBO productBo = new ItemBOImpl();
    OrderDetailsBO orderDetailsBo = new OrderDetailsBOImpl();
    boolean isPlaced = false;

    static Logger logger = LoggerFactory.getLogger(OrderController.class);

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
        Jsonb jsonb = JsonbBuilder.create();
        List<OrderDetailsDTO> detailsDto = jsonb.fromJson(req.getReader(), new ArrayList<OrderDetailsDTO>(){}.getClass().getGenericSuperclass());

        OrderDTO orderDto = new OrderDTO(
                detailsDto.get(0).getOrderId(),
                detailsDto.get(0).getCusId(),
                LocalDate.now()
        );

        List<ItemDTO> productList = new ArrayList<>();

        for (OrderDetailsDTO dto : detailsDto) {

            productList.add(new ItemDTO(
                    dto.getProId(),
                    null,
                    0,
                    dto.getQty()
            ));
        }

        placeOrder(detailsDto,orderDto,productList);

        if (isPlaced) {
            resp.getWriter().println("Placed");
        }

    }

    public void placeOrder( List<OrderDetailsDTO> detailsDto, OrderDTO orderDto,List<ItemDTO> productList){
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                try{
                    connection.setAutoCommit(false);

                    boolean isSaved = orderBo.saveOrder(orderDto, connection);

                    if (isSaved) {
                        System.out.println("Saved");

                        boolean isUpdated = false;

                        for (ItemDTO productDto : productList) {
                            isUpdated = productBo.updateItemQty(productDto, connection);
                        }

                        if (isUpdated) {
                            logger.info("Order Placed");
                            boolean isDetailSaved = false;

                            for (OrderDetailsDTO dto : detailsDto) {
                                isDetailSaved = orderDetailsBo.saveOrderDetails(dto, connection);
                            }

                            System.out.println(isDetailSaved);

                            if (isDetailSaved) {
                                isPlaced =  true;
                                connection.commit();
                            }else{
                                connection.rollback();
                            }


                        }else{
                            connection.rollback();
                        }

                    }else{
                        connection.rollback();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    connection.setAutoCommit(true);
                }
            }
        }).start();

    }

}
