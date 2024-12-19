package Shop.Online_Shop.rest;

import Shop.Online_Shop.service.impl.BagServiceImpl;
import Shop.Online_Shop.model.dto.BagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bag")
public class BagController {

    @Autowired
    private BagServiceImpl bagServiceImpl;

    @GetMapping
    public List<BagDto> getAllBagDto(){
        return bagServiceImpl.getAllBagDto();
    }
    @GetMapping("{id}")
    public BagDto getBagDto(@PathVariable(value = "id")Long id){
        return  bagServiceImpl.getBagDto(id);
    }
    @GetMapping("/checkout/{userId}")
    public String checkout(@PathVariable(value = "userId")Long userId){
        return bagServiceImpl.checkout(userId);
    }

    @PostMapping("/addProduct/{productId}/{userId}")
    public void addProductInBag(@PathVariable(value = "productId")Long productId, @PathVariable(value = "userId") Long userId){
        bagServiceImpl.addProductInBag(productId,userId);
    }

    @DeleteMapping ("/deleteProductInBag/{productId}/{userId}")
    public void deleteProductInBag(@PathVariable(value = "productId") Long productId, @PathVariable(value = "userId") Long userId){
        bagServiceImpl.deleteProductInBag(productId, userId);
    }
}
