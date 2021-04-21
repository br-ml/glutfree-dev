package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>  {


    Optional<UserEntity> findByUsernameAndPassword (String username, String password);

    Optional<UserEntity> findByUsername (String username);

    @Query("select u.username from UserEntity u order by u.username asc")
    List<String> findAllUserNames();


    void deleteByUsername(String username);

}