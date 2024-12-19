package Shop.Online_Shop.mapper;


import Shop.Online_Shop.model.dto.BagDto;
import Shop.Online_Shop.model.Bag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BagMapper {
    BagDto bagToDto(Bag bag);

    Bag bagToEntity(BagDto bagDto);

    List<BagDto> bagListToDto(List<Bag> bags);

    List<Bag> bagListToEntity(List<BagDto> bagDtos);
}
