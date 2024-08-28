package lk.ijse.aadassignment1backendapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private int tel;
    private String name;
    private String address;
    private String email;
}
