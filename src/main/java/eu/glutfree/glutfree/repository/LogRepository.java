//
//package eu.glutfree.glutfree.repository;
//
//import eu.glutfree.glutfree.model.entities.FoodEntity;
//import eu.glutfree.glutfree.model.entities.LogEntity;
//import eu.glutfree.glutfree.model.service.LogServiceModel;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.lang.reflect.Array;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public interface LogRepository extends JpaRepository<LogEntity, Long> {
//
////    List<LogEntity> findTopBy;
//
//    @Query(nativeQuery = true, value = "Select p.food_entity_id from (SELECT l.food_entity_id, COUNT(l.id) as countlist FROM logs as l GROUP BY l.food_entity_id ORDER BY countlist DESC LIMIT 1) as p")
//     Long findTopfood();
//
//    @Query(nativeQuery = true, value = "Select p.food_entity_id from (SELECT l.food_entity_id, COUNT(l.id) as countlist FROM logs as l GROUP BY l.food_entity_id ORDER BY countlist DESC LIMIT 6) as p")
//    List<Long> findTopThreefood();
//
//    void deleteAllByFoodEntity_Id(Long id);
//
//
////    @Query(nativeQuery = true, value = "SELECT l.food_entity_id.id, COUNT(l.id) as lCount FROM logs l GROUP BY l.food_entity_id.id order by lCount desc LIMIT 3 ")
////    HashMap<Long, Integer> findTopThreefoods();
//
////
////    @Query(nativeQuery = true, value = "SELECT l, COUNT(l.id) as lCount FROM LogEntity l GROUP BY l.foodEntity.id order by lCount desc LIMIT 3 ")
////    List<LogEntity> findTopThreeFoodsAsLogs();
//
//
//}