package ted.getaroom.controllers;

import org.springframework.web.bind.annotation.*;
import ted.getaroom.models.User;
import ted.getaroom.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> all() {
        return userRepository.findAll();
    }

    @PostMapping
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/{id}")
    public User one(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElse(new User());
    }
}
