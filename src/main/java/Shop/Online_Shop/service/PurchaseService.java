package Shop.Online_Shop.service;

import Shop.Online_Shop.model.dto.PurchaseDto;
import java.util.List;

public interface PurchaseService {

    // Получить список всех покупок пользователя по его ID
    List<PurchaseDto> getPurchasesByUserId(Long userId);

}

