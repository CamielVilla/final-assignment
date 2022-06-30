package nl.novi.assigment.homecare.controller;


import nl.novi.assigment.homecare.model.dto.AuthorityDto;
import nl.novi.assigment.homecare.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("login")
public class AuthorityController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    JwtService jwtService;


    @PostMapping()
    public ResponseEntity<Object> signIn(@RequestBody AuthorityDto authorityDto) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authorityDto.getEmail(), authorityDto.getPassword());
        Authentication auth = authManager.authenticate(up);

        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(ud);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(token);
    }
}
