package nl.novi.assigment.homecare.service;
import nl.novi.assigment.homecare.model.dto.PasswordDto;
import nl.novi.assigment.homecare.model.dto.UserDto;
import nl.novi.assigment.homecare.model.entity.User;
import nl.novi.assigment.homecare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto toUserDto (User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public User toUser(UserDto userDto){
        User user = userRepository.findById(userDto.getId()).get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        return user;
    }



    public List<UserDto> getAllUsers(){
    List<User> users = userRepository.findAllUsers();
    List<UserDto> dtos = new ArrayList<>();
    for (User u : users){
        dtos.add(toUserDto(u));
    }
    return dtos;
    }

    public UserDto getUserById(Long id){
        if (userRepository.existsById(id)){
            return toUserDto(userRepository.findById(id).get());
        }else{
            throw new RuntimeException();
        }
    }

    public UserDto updateUserPassword(Long id, PasswordDto dto) {
        User user = userRepository.getById(id);
        if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword()) && dto.getNewPassword().equals(dto.getRepeatNewPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            userRepository.save(user);
            return toUserDto(user);
        } else {
            throw new RuntimeException();
        }
    }
}
