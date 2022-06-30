package nl.novi.assigment.homecare.controller;

import nl.novi.assigment.homecare.model.dto.PasswordDto;
import nl.novi.assigment.homecare.model.dto.UserDto;
import nl.novi.assigment.homecare.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("{id}/password")
    ResponseEntity<UserDto> updateUserPassword(@PathVariable Long id, @Valid @RequestBody PasswordDto passWordDto)  {
        userService.updateUserPassword(id, passWordDto);
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
