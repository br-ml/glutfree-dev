package eu.glutfree.glutfree.service;

import eu.glutfree.glutfree.model.service.FeedbackAddServiceModel;
import eu.glutfree.glutfree.model.service.FoodAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;

import java.io.IOException;
import java.util.List;

public interface FeedbackService {

    void addFeedback (FeedbackAddServiceModel feedbackAddServiceModel) throws IOException;

    List<FeedbackViewModel> findAllFeedbacks();
    List<FeedbackViewModel> findLatestAdded6Feedbacks();

    void delete(Long id);

    FeedbackViewModel findById(Long id);

}
