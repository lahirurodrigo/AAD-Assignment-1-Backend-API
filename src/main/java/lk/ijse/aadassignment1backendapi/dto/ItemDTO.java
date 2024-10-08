package lk.ijse.aadassignment1backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String id;
    private String name;
    private double price;
    private double qty;
}
