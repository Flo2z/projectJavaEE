package Shop.Online_Shop.service;

import Shop.Online_Shop.model.dto.UserDto;
import Shop.Online_Shop.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto addUserDto(User user);
    UserDto getUser(Long id);
    UserDto updateUser(Long id,UserDto userDto);
    String updatePassword(User user);
    void deleteUserById(Long id);
    List<UserDto> searchUsers(String fullName);
    double getUserBalance(Long userId);
    void setBalance(Long userId, double amount);
}
