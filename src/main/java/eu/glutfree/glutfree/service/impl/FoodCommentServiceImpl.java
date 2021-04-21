//package eu.glutfree.glutfree.service.impl;
//
//import eu.glutfree.glutfree.model.entities.FoodCommentEntity;
//import eu.glutfree.glutfree.model.entities.FoodEntity;
//import eu.glutfree.glutfree.model.entities.UserEntity;
//import eu.glutfree.glutfree.model.service.FoodCommentServiceModel;
//import eu.glutfree.glutfree.repository.CommentRepository;
//import eu.glutfree.glutfree.service.FoodCommentService;
//import eu.glutfree.glutfree.service.FoodService;
//import eu.glutfree.glutfree.service.UserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class FoodCommentServiceImpl implements FoodCommentService {
//
//    private final FoodService foodService;
//    private final UserService userService;
//    private final CommentRepository commentRepository;
//    private final ModelMapper modelMapper;
//
//
//    public FoodCommentServiceImpl(FoodService foodService, UserService userService, CommentRepository commentRepository, ModelMapper modelMapper) {
//        this.foodService = foodService;
//
//        this.userService = userService;
//        this.commentRepository = commentRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public List<FoodCommentServiceModel> getAllCommentsByFoodIdSorted(Long id) {
//        List<FoodCommentServiceModel> result = this.commentRepository.
//                findAllByNewsId(id).
//                stream().
//                map(c -> modelMapper.map(c, FoodCommentServiceModel.class)).
//                collect(Collectors.toList());
//
//
//        return result;
//    }
//
//    @Override
//    public void addCommentToFood (FoodCommentServiceModel foodcommentServiceModel)  {
////        FoodCommentEntity foodcommentEntity = this.modelMapper.map(foodcommentServiceModel, FoodCommentEntity.class);
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////
////        FoodEntity foodEntity = modelMapper.map(this.foodService.getFoodById(foodcommentServiceModel.getFoodId()), FoodEntity.class);
////        foodcommentEntity.setAuthor(userService.findUserByUsername(authentication.getName()));
////        foodcommentEntity.setFoodEntity(foodEntity);
////        foodcommentEntity.setAddedOn(LocalDateTime.now());
////        this.commentRepository.saveAndFlush(foodcommentEntity);
//    }
//}
