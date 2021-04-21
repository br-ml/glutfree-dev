package eu.glutfree.glutfree.repository;

import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    List<ReceiptEntity> findTop6ByIdIsNotNullOrderByIdDesc();


}
