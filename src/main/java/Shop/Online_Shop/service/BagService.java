
package Shop.Online_Shop.service;

import Shop.Online_Shop.model.dto.BagDto;
import Shop.Online_Shop.model.User;

import java.util.List;

public interface BagService {
    List<BagDto> getAllBagDto();
    BagDto getBagDto(Long id);
    void addProductInBag(Long productId,Long userId);
    void deleteProductInBag(Long productId,Long userId);
    String checkout(Long userId);
    void createBagForUser(User user);

}
