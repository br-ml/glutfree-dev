//package eu.glutfree.glutfree.repository;
//
//import eu.glutfree.glutfree.model.entities.FoodCommentEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface CommentRepository extends JpaRepository<FoodCommentEntity, Long> {
//
//    @Query("SELECT c FROM FoodCommentEntity c WHERE c.newsEntity.id = ?1 ORDER BY c.addedOn ASC")
//    List<FoodCommentEntity> findAllByNewsId(Long id);
//}
