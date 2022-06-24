package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.UserDto;
import nl.novi.assigment.homecare.model.entity.User;
import nl.novi.assigment.homecare.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers (){
       return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById (@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
