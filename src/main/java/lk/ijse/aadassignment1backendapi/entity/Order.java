package lk.ijse.aadassignment1backendapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order{
    private String orderId;
    private String cusId;
    private LocalDate date;
}
