package nl.novi.assigment.homecare.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.novi.assigment.homecare.model.entity.User;
import nl.novi.assigment.homecare.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;


@Service
public class JwtService  {


    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final static String SECRET_KEY = "ditisdesecretkeyvanhomecare";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return
                Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            Optional<User> optionalUser = userRepository.findUserByEmail(userDetails.getUsername());
            User user = optionalUser.get();
            Long id = user.getId();
            String userId = id.toString();
            String role = user.getRole();
            return createToken(claims, userDetails.getUsername(), userId, role);
        }


    private String createToken(Map<String, Object> claims, String
            subject, String id, String role) {
        long validPeriod = 1000 * 60 * 60 * 24;
        long currentTime = System.currentTimeMillis();

        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTime))
                .setAudience(role)
                .setExpiration(new Date(currentTime + validPeriod))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails
            userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }

}
