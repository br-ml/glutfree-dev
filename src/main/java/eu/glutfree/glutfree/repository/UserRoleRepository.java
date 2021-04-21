package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.UserRoleEntity;
import eu.glutfree.glutfree.model.entities.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByRole(UserRoleEnum userRoleEnum);
}