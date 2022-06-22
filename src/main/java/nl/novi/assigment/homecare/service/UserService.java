package nl.novi.assigment.homecare.service;
import nl.novi.assigment.homecare.model.dto.UserDto;
import nl.novi.assigment.homecare.model.entity.User;
import nl.novi.assigment.homecare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto toUserDto (User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }



    public List<UserDto> getAllUsers(){
    List<User> users = userRepository.findAllUser();
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

}
