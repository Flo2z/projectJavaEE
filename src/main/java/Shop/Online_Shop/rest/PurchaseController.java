package Shop.Online_Shop.rest;


import Shop.Online_Shop.service.PurchaseService;
import Shop.Online_Shop.model.dto.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("{userId}")
    public List<PurchaseDto> getPurchasesByUserId(@PathVariable(value = "userId") Long userId){
        return purchaseService.getPurchasesByUserId(userId);
    }
}
