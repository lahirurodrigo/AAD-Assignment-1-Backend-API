package lk.ijse.aadassignment1backendapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String id;
    private String name;
    private double price;
    private double qty;
}
