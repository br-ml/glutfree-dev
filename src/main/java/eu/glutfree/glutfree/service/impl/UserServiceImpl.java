package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.exceptions.RoleNotFoundException;
import eu.glutfree.glutfree.exceptions.UserNotFoundException;
import eu.glutfree.glutfree.model.entities.UserEntity;
import eu.glutfree.glutfree.model.entities.UserRoleEntity;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;
import eu.glutfree.glutfree.model.service.UserRegistrationServiceModel;
import eu.glutfree.glutfree.repository.UserRepository;
import eu.glutfree.glutfree.repository.UserRoleRepository;
import eu.glutfree.glutfree.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final GlutfreeDBUserService glutfreeDBUserService;


    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper, GlutfreeDBUserService glutfreeDBUserService) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.glutfreeDBUserService = glutfreeDBUserService;
    }

    @Async
    @Override
    public void seedUsers() {

        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setEmail("admin@admin.com").setPassword(passwordEncoder.encode("admin"));

            admin.setRoles(List.of(adminRole, userRole));

            userRepository.saveAll(List.of(admin));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.
                findByRole(UserRoleEnum.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = glutfreeDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);//TODO
    }

    @Override
    public void updateUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserEntity currentUser = userRepository.findById(userRegistrationServiceModel.getId()).orElseThrow(
                () -> new UserNotFoundException("USER  not found."));

        currentUser.setRegion(userRegistrationServiceModel.getRegion());
        currentUser.setFirstName(userRegistrationServiceModel.getFirstName());
        currentUser.setSecondName(userRegistrationServiceModel.getSecondName());
        userRepository.saveAndFlush(currentUser);

    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);


    }


    @Override
    public List<String> findAllUserNames() {
        return userRepository.findAllUserNames();
    }


    @Override
    public void changeRole(String username, String role) {
        boolean hasRole = false;
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("This User does not exist in the database!"));
        List<UserRoleEntity> userRolls = userEntity.getRoles();
        UserRoleEntity userRoleEntity = this.userRoleRepository.findByRole(UserRoleEnum.valueOf(role)).orElseThrow(() -> new RoleNotFoundException("This Role does not exist in the database!"));
        for (UserRoleEntity userRoll : userRolls) {
            if (userRoll.getRole().equals(UserRoleEnum.valueOf(role))) {
                hasRole = true;
            }
        }
        if (!hasRole) {
            userEntity.getRoles().add(userRoleEntity);
            userRepository.save(userEntity);
        }

    }

    @Override
    public void deleteRole(String username, String role) {
        boolean hasRole = false;
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("This User does not exist in the database!"));
        List<UserRoleEntity> userRolls = userEntity.getRoles();
        List<UserRoleEntity> newUserRolls = new ArrayList<>();
        UserRoleEntity userRoleEntity = this.userRoleRepository.findByRole(UserRoleEnum.valueOf(role)).orElseThrow(() -> new RoleNotFoundException("This Role does not exist in the database!"));
        for (UserRoleEntity userRoll : userRolls) {
            if (userRoll.getRole().equals(UserRoleEnum.valueOf(role))) {
                hasRole = true;
            }
        }
        if (hasRole) {
            for (UserRoleEntity userRoll : userRolls) {
                if (!userRoll.getRole().equals(UserRoleEnum.valueOf(role))) {
                    newUserRolls.add(userRoll);

                }

            }

        }
        userEntity.setRoles(newUserRolls);
        userRepository.save(userEntity);
    }
    @Transactional
    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }


}