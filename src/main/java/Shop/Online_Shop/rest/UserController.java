package Shop.Online_Shop.rest;

import Shop.Online_Shop.service.impl.UserServiceImpl;
import Shop.Online_Shop.model.dto.UserDto;
import Shop.Online_Shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceimpl;
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userServiceimpl.getAllUsers();
    }
    @GetMapping(value="{id}")
    public UserDto getUser(@PathVariable(value="id") Long id){
        return userServiceimpl.getUser(id);
    }

    @GetMapping(value = "/searchUsers/{fullName}")
    public List<UserDto> searchUserByFullName(@PathVariable(value="fullName") String fullName){
        return userServiceimpl.searchUsers(fullName);
    }

    @GetMapping("/getUserBalance/{userId}")
    public double getUserBalance(@PathVariable(value = "userId") Long userId){
        return userServiceimpl.getUserBalance(userId);
    }

    @PostMapping
    public UserDto addUser(@RequestBody User user){
        return userServiceimpl.addUserDto(user);
    }

    @PostMapping("/setBalance/{userId}/{amount}")
    public void setBalance(@PathVariable(value = "userId") Long userId, @PathVariable(value = "amount") double amount){
        userServiceimpl.setBalance(userId, amount);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable(value = "id") Long id,@RequestBody UserDto userDto){
        return userServiceimpl.updateUser(id,userDto);
    }
    @PutMapping("/password")
    public String updatePassword(@RequestBody User user){
        return userServiceimpl.updatePassword(user);
    }
    @DeleteMapping(value = "{id}")
    public void deleteUserById(@PathVariable(value="id") Long id){
        userServiceimpl.deleteUserById(id);
    }
}
