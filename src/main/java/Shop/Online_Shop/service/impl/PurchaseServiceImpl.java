package Shop.Online_Shop.service.impl;

import Shop.Online_Shop.model.dto.PurchaseDto;
import Shop.Online_Shop.mapper.PurchaseMapper;
import Shop.Online_Shop.model.Purchase;
import Shop.Online_Shop.repository.PurchaseRepository;
import Shop.Online_Shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<PurchaseDto> getPurchasesByUserId(Long userId) {
        List<Purchase> purchases = purchaseRepository.findByUserId(userId);
        return purchaseMapper.toDtoList(purchases);
    }
}
