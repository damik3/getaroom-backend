package ted.getaroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.User;
import ted.getaroom.myExceptions.ForbiddenException;
import ted.getaroom.repositories.UserRepository;
import ted.getaroom.security.jwt.JwtUtils;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
//@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<User> all() {
        return userRepository.findAll();
    }

    @PostMapping
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    // Allow only users with matching IDs to access this resource
    public User one(@RequestHeader HttpHeaders headers, @PathVariable Long id) {

        // Get token from Authorization field
        String jwt = headers.getFirst(HttpHeaders.AUTHORIZATION);

        // Remove substring 'Bearer' from the start of the string
        if (jwt.startsWith("Bearer"))
            jwt = jwt.substring(7, jwt.length());



        // If it is a valid jwt
        if (jwt != null  &&  jwtUtils.validateJwtToken(jwt)) {

            // Get username from jwt
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            // Load user by username
            User user = userRepository.findByUsername(username).orElse(null);

            // If user is found and has matching IDs then allow
            if (user != null && user.getId() == id) {
                return user;
            }

            // else return Forbidden
            else
                throw new ForbiddenException();
        }



        // If it is not a valid jwt token,
        // or if it the ids are not matching,
        else
            throw new ForbiddenException();
    }



    @PutMapping("/{id}")
    // Allow only users with matching IDs to access this resource
    public User updateUser(@RequestHeader HttpHeaders headers, @RequestBody User newUser, @PathVariable Long id) {

        // Get token from Authorization field
        String jwt = headers.getFirst(HttpHeaders.AUTHORIZATION);

        // Remove substring 'Bearer' from the start of the string
        if (jwt.startsWith("Bearer"))
            jwt = jwt.substring(7, jwt.length());



        // If it is a valid jwt
        if (jwt != null  &&  jwtUtils.validateJwtToken(jwt)) {

            // Get username from jwt
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            // Load user by username
            User user = userRepository.findByUsername(username).orElse(null);

            // If user is found and has matching IDs then allow
            if (user != null && user.getId() == id) {

                // Update user
                user.setName(newUser.getName());
                user.setSurname(newUser.getSurname());
                user.setEmail(newUser.getEmail());
                user.setPhone(newUser.getPhone());

                // Save and return
                return userRepository.save(user);
            }

            // else return Forbidden
            else
                throw new ForbiddenException();
        }



        // If it is not a valid jwt token,
        // or if it the ids are not matching,
        else
            throw new ForbiddenException();
    }
}
