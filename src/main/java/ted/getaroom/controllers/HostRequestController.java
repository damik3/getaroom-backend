package ted.getaroom.controllers;

import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ted.getaroom.models.ERole;
import ted.getaroom.models.HostRequest;
import ted.getaroom.models.Role;
import ted.getaroom.models.User;
import ted.getaroom.payload.response.MessageResponse;
import ted.getaroom.repositories.HostRequestRepository;
import ted.getaroom.repositories.RoleRepository;
import ted.getaroom.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/host-request")
public class HostRequestController {

    private final UserRepository userRepository;
    private final HostRequestRepository hostRequestRepository;
    private final RoleRepository roleRepository;

    public HostRequestController(UserRepository userRepository, HostRequestRepository hostRequestRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hostRequestRepository = hostRequestRepository;
        this.roleRepository = roleRepository;
    }



    @PostMapping("/{id}")
    public ResponseEntity<?> approveRequest(@PathVariable Long id) {

        // Find user
        User user = this.userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found!"));
        }

        // Find host request
        HostRequest hostRequest = this.hostRequestRepository.findByUserId(user.getId()).orElse(null);
        if (hostRequest == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Request not found!"));
        }

        // Find host role
        Role hostRole = roleRepository.findByName(ERole.ROLE_HOST)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        // Add role
        Set<Role> roles = user.getRoles();
        roles.add(hostRole);
        user.setRoles(roles);
        user.setHostReqPending(false);

        // Update user
        this.userRepository.save(user);

        // Delete row from table
        this.hostRequestRepository.deleteByUserId(user.getId());

        // Return ok
        return ResponseEntity.ok(new MessageResponse("Request approved!"));
    }
}
