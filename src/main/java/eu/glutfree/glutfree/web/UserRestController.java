package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.service.UserRegistrationServiceModel;
import eu.glutfree.glutfree.model.view.UserViewModel;
import eu.glutfree.glutfree.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/api/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationServiceModel model) {
        if (userService.userNameExists(model.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        userService.registerAndLoginUser(model);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/me")
    public ResponseEntity<UserViewModel> me(Authentication authentication) {
        UserEntity user = userService.findUserByUsername(authentication.getName());
        UserViewModel viewModel = modelMapper.map(user, UserViewModel.class);
        viewModel.setRoles(user.getRoles().stream()
                .map(r -> r.getRole().name())
                .collect(Collectors.toList()));
        return ResponseEntity.ok(viewModel);
    }

    @PutMapping("/api/me")
    public ResponseEntity<Void> update(@RequestBody UserRegistrationServiceModel model,
                                       Authentication authentication) {
        UserEntity user = userService.findUserByUsername(authentication.getName());
        model.setId(user.getId());
        userService.updateUser(model);
        return ResponseEntity.ok().build();
    }
}
