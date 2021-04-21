package eu.glutfree.glutfree.service;

import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.service.FoodAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;

import java.io.IOException;
import java.util.List;

public interface FoodService {

    void addFood (FoodAddServiceModel foodAddServiceModel) throws IOException;

    List<FoodViewModel> findAllFoods();


    List<FoodViewModel> findLatest6TestedFoods();
    List<FoodViewModel> findLatest6Foods();

    void delete(Long id);

    FoodViewModel findById(Long id);

    FoodEntity findEntityById(Long foodId);



//    List<FoodViewModel> findTopThreeViewedFoods();

}
