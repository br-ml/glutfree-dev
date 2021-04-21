package eu.glutfree.glutfree.service.impl;


import eu.glutfree.glutfree.model.entities.UserRoleEntity;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;
import eu.glutfree.glutfree.repository.UserRoleRepository;
import eu.glutfree.glutfree.service.UserRollEntityService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleEntityServiceImpl implements UserRollEntityService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleEntityServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleEntity findByRole(UserRoleEnum role) {
        return userRoleRepository.findByRole(role).orElse(null);
    }
}
