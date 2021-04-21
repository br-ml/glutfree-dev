package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    Optional<StoreEntity> findByName (String username);

    @Query("SELECT s.name FROM StoreEntity  s ORDER BY s.name")
    List<String> findAllStores();
}
