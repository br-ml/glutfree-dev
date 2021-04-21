package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository  extends JpaRepository<FeedbackEntity, Long> {

    List<FeedbackEntity> findTop6ByIdIsNotNullOrderByIdDesc();
}
