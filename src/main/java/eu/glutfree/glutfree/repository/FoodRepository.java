package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {


        List<FoodEntity> findAllByNimaTestedIsTrue();

        List<FoodEntity>  findTop6ByNimaTestedIsTrueOrderByIdDesc();
        List<FoodEntity> findTop6ByIdIsNotNullOrderByIdDesc();



}
