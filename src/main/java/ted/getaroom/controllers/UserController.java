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

        User user = null;

        // Get token from Authorization field
        String jwt = headers.getFirst(HttpHeaders.AUTHORIZATION);
        //System.out.println("Contents of auth header = " + jwt);

        if (jwt.startsWith("Bearer"))
            jwt = jwt.substring(7, jwt.length());

        // If it is a valid jwt token
        if (jwt != null  &&  jwtUtils.validateJwtToken(jwt)) {

            // Load username
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            //System.out.println("jwt username = " + username);

            user = userRepository.findByUsername(username).orElse(null);

            if (user != null && user.getId() == id) {
                //System.out.println("VOILA");
                return user;
            }

            else
                throw new ForbiddenException();
        }

        // If it is not a valid jwt token,
        // or if it the ids are not matching,
        else
            throw new ForbiddenException();
    }
}
