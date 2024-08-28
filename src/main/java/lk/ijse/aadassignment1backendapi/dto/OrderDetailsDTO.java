package lk.ijse.aadassignment1backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private String orderId;
    private String cusId;
    private String proId;
    private double qty;
    private double price;
}
