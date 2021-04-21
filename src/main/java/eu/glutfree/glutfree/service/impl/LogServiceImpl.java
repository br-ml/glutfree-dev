//package eu.glutfree.glutfree.service.impl;
//
//
//import eu.glutfree.glutfree.model.entities.FoodEntity;
//import eu.glutfree.glutfree.model.entities.LogEntity;
//import eu.glutfree.glutfree.model.entities.UserEntity;
//import eu.glutfree.glutfree.model.service.LogServiceModel;
//import eu.glutfree.glutfree.model.view.FoodViewModel;
//import eu.glutfree.glutfree.repository.LogRepository;
//import eu.glutfree.glutfree.service.FoodService;
//import eu.glutfree.glutfree.service.LogService;
//import eu.glutfree.glutfree.service.UserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Array;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class LogServiceImpl implements LogService {
//
//    private final LogRepository logRepository;
//    private final FoodService foodService;
////    private final UserService userService;
//    private final ModelMapper modelMapper;
//
//    public LogServiceImpl(LogRepository logRepository, FoodService foodService,  ModelMapper modelMapper) {
//        this.logRepository = logRepository;
//        this.foodService = foodService;
////        this.userService = userService;
//        this.modelMapper = modelMapper;
//    }
//
//    @Override
//    public void createLog(String action, Long foodId) {
//        FoodEntity foodEntity = foodService
//                .findEntityById(foodId);
////
////        Authentication authentication = SecurityContextHolder
////                .getContext()
////                .getAuthentication();
////
////        String username = authentication.getName();
////        UserEntity userEntity = userService.findByName(username);
//
//        LogEntity logEntity = new LogEntity()
//                .setFoodEntity(foodEntity)
////                .setUserEntity(userEntity)
//                .setAction(action)
//                .setDateTime(LocalDateTime.now());
//
//        logRepository.save(logEntity);
//
//    }
//
//    @Override
//    public List<LogServiceModel> findAllLogs() {
//        return logRepository
//                .findAll()
//                .stream()
//                .map(logEntity -> {
//                    LogServiceModel logServiceModel = modelMapper
//                            .map(logEntity, LogServiceModel.class);
//                    logServiceModel.setFood(logEntity.getFoodEntity().getName());
////                    logServiceModel.setUser(logEntity.getUserEntity().getUsername());
//
//                    return logServiceModel;
//                })
//                .collect(Collectors.toList());
//    }
//
//
//
//
//    @Override
//    public FoodViewModel findTopViewedFoods() {
//
//        Long mapWithTopFood = this.logRepository.findTopfood();
//        FoodViewModel foodViewModel = foodService.findById(mapWithTopFood);
//
//        return foodViewModel;
//
//    }
//
//
//
//    @Override
//    public List<FoodViewModel> findTopThreeViewedFoods() {
//        return this.logRepository.findTopThreefood().stream().map(foodId -> {
//
//
//            FoodViewModel foodViewModela = foodService.findById(foodId);
//            FoodViewModel foodViewModel = this.modelMapper.map( foodViewModela , FoodViewModel.class);
//
////            foodViewModel.setStore(foodService.findById(foodId).getStore());
////            foodViewModel.setStorelogoUrl(foodService.findById(foodId).get);
//            return foodViewModel;
//        }).collect(Collectors.toList());
//
//    }
//    @Transactional
//    @Override
//    public void deleteAllById(Long id) {
//        logRepository.deleteAllByFoodEntity_Id(id);
//    }
//
//}