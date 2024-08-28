package lk.ijse.aadassignment1backendapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    private String orderId;
    private String cusId;
    private String proId;
    private double qty;
    private double price;

}
