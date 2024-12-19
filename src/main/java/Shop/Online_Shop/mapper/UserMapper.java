package Shop.Online_Shop.mapper;

import Shop.Online_Shop.model.dto.UserDto;
import Shop.Online_Shop.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);

    List<User> toEntitiyList(List<UserDto> userDtos);
}
