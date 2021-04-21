package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.model.entities.FeedbackEntity;
import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import eu.glutfree.glutfree.model.service.FeedbackAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.repository.FeedbackRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import eu.glutfree.glutfree.service.FeedbackService;
import eu.glutfree.glutfree.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final ModelMapper modelMapper;
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    public FeedbackServiceImpl(ModelMapper modelMapper, FeedbackRepository feedbackRepository, UserService userService,CloudinaryService cloudinaryService ) {
        this.modelMapper = modelMapper;
        this.feedbackRepository = feedbackRepository;
        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public void addFeedback(FeedbackAddServiceModel feedbackAddServiceModel) throws IOException {

        MultipartFile img = feedbackAddServiceModel.getImage();
        String imageUrl = cloudinaryService.uploadImage(img);


        FeedbackEntity feedback = modelMapper.map(feedbackAddServiceModel, FeedbackEntity.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        feedback.setUser(userService.findUserByUsername(authentication.getName()));
        feedback.setUrlToPic(imageUrl);
        feedbackRepository.save(feedback);
    }


    @Override
    public List<FeedbackViewModel> findAllFeedbacks() {
        return this.feedbackRepository.findAll().stream().map( feedbackEntity -> {
            FeedbackViewModel feedbackViewModel = this.modelMapper.map( feedbackEntity , FeedbackViewModel.class);
//            feedbackViewModel.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity.getName() ));
            return feedbackViewModel;
        }).collect(Collectors.toList());

    }

    @Override
    public List<FeedbackViewModel> findLatestAdded6Feedbacks() {
        return this.feedbackRepository.findTop6ByIdIsNotNullOrderByIdDesc().stream().map( feedbackEntity -> {
            FeedbackViewModel feedbackViewModel = this.modelMapper.map( feedbackEntity , FeedbackViewModel.class);
//            feeddddsackViewModel.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", feedbackEntity.getName() ));
            return feedbackViewModel;
        }).collect(Collectors.toList());

    }

    @Override
    public void delete(Long id) {
        feedbackRepository.deleteById(id);

    }



    @Override
    public FeedbackViewModel findById(Long id) {
        return feedbackRepository
                .findById(id)
                .map(feedbackEntity -> {
                    FeedbackViewModel feedbackViewModel = modelMapper
                            .map(feedbackEntity, FeedbackViewModel.class);
                    return feedbackViewModel;
                })
                .orElseThrow(IllegalArgumentException::new);
    }

}
