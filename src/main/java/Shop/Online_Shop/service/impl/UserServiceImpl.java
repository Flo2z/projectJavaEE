package Shop.Online_Shop.service.impl;

import Shop.Online_Shop.service.BagService;
import Shop.Online_Shop.service.UserService;
import Shop.Online_Shop.model.dto.UserDto;
import Shop.Online_Shop.mapper.UserMapper;
import Shop.Online_Shop.model.User;
import Shop.Online_Shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BagService bagService;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.toDtoList(userList);
    }


    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }

    @Override
    public UserDto addUserDto(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        User newUser = userRepository.save(user);

        bagService.createBagForUser(newUser);

        return userMapper.toDto(newUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User userToUpdate = userRepository.findById(id).orElseThrow();
        userToUpdate.setFullName(userDto.getFullName());
        userToUpdate.setAge(userDto.getAge());
        User updatedUser = userRepository.save(userToUpdate);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String updatePassword(User user) {
        User currentUser = userRepository.findByEmail(user.getEmail());
        if (currentUser != null) {
            currentUser.setPassword(user.getPassword());
            userRepository.save(currentUser);
            return "success";
        }
        return "error";
    }

    @Override
    public List<UserDto> searchUsers(String fullName) {
        List<User> userList = userRepository.findAllByFullNameIgnoreCaseContaining(fullName);
        return userMapper.toDtoList(userList);
    }

    @Override
    public double getUserBalance(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getBalance();
    }

    @Override
    public void setBalance(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
}
