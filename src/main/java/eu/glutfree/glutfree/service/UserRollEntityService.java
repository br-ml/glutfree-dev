package eu.glutfree.glutfree.service;


import eu.glutfree.glutfree.model.entities.UserRoleEntity;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;

public interface UserRollEntityService {
    UserRoleEntity findByRole(UserRoleEnum role);
}
