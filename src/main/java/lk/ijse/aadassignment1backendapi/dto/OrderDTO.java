package lk.ijse.aadassignment1backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private String orderId;
    private String cusId;
    private LocalDate date;

}
