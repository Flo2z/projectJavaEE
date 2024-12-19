package Shop.Online_Shop.mapper;

import Shop.Online_Shop.model.dto.PurchaseDto;
import Shop.Online_Shop.model.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PurchaseMapper {

    @Mapping(source = "user", target = "userDto")
    PurchaseDto toDto(Purchase purchase);

    Purchase toEntity(PurchaseDto purchaseDto);

    List<PurchaseDto> toDtoList(List<Purchase> purchases);

    List<Purchase> toEntityList(List<PurchaseDto> purchaseDtos);
}
