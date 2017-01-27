package supermarket.orders.boundary;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data

public class OrderDTO {

    @NotEmpty(message = "Nª Bill can't be empty")
    String idBill;

    @Size(min = 2, max = 14, message = "Customer name must be between 2 and 14 characters long.")
    String customer;

}
