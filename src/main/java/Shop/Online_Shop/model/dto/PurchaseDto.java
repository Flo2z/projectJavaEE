package Shop.Online_Shop.model.dto;

import Shop.Online_Shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private UserDto userDto;
    private List<Product> products;
    private LocalDateTime purchaseTime;
    private double totalPrice;
}
