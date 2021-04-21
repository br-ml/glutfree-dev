package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.model.entities.FoodEntity;
import eu.glutfree.glutfree.model.entities.StoreEntity;
import eu.glutfree.glutfree.model.service.FoodAddServiceModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.repository.FoodRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import eu.glutfree.glutfree.service.FoodService;
import eu.glutfree.glutfree.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class    FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;
    private final StoreService storeService;
    private final CloudinaryService cloudinaryService;



    public FoodServiceImpl(ModelMapper modelMapper, FoodRepository foodRepository, StoreService storeService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.foodRepository = foodRepository;
        this.storeService = storeService;
        this.cloudinaryService = cloudinaryService;

    }


    @Override
    public void addFood(FoodAddServiceModel foodAddServiceModel) throws IOException {


        MultipartFile img = foodAddServiceModel.getImage();
        String imageUrl = cloudinaryService.uploadImage(img);

        MultipartFile imgLabel = foodAddServiceModel.getLabelImage();
        String imageUrlLabel = cloudinaryService.uploadImage(imgLabel);

        FoodEntity food = modelMapper.map(foodAddServiceModel, FoodEntity.class);
        StoreEntity store = storeService.findStoreByName(foodAddServiceModel.getStore());
        food.setStore(store);
        food.setUrlToPic(imageUrl);
        food.setUrlToLabelImage(imageUrlLabel);
        foodRepository.save(food);
    }

    @Override
    public List<FoodViewModel> findAllFoods() {
        return this.foodRepository.findAll().stream().map( foodEntity -> {
            FoodViewModel foodViewModel = this.modelMapper.map( foodEntity , FoodViewModel.class);
            foodViewModel.setStorelogoUrl(foodEntity.getStore().getLogoUrl());
//            foodViewModel.setLogoPicture(String.format("/img/feedbacks/logo/%s.jpg", foodEntity.getName() ));
            return foodViewModel;
        }).collect(Collectors.toList());

    }


    @Override
    public List<FoodViewModel> findLatest6Foods() {
        return this.foodRepository.findTop6ByIdIsNotNullOrderByIdDesc().stream().map(foodEntity -> {
            FoodViewModel foodViewModel = this.modelMapper.map( foodEntity , FoodViewModel.class);
            foodViewModel.setStorelogoUrl(foodEntity.getStore().getLogoUrl());
            return foodViewModel;
        }).collect(Collectors.toList());

    }

    @Override
    public List<FoodViewModel> findLatest6TestedFoods() {
        return this.foodRepository.findTop6ByNimaTestedIsTrueOrderByIdDesc().stream().map( foodEntity -> {
            FoodViewModel foodViewModel = this.modelMapper.map( foodEntity , FoodViewModel.class);
            foodViewModel.setStorelogoUrl(foodEntity.getStore().getLogoUrl());
            return foodViewModel;
        }).collect(Collectors.toList());

    }


    @Override
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public FoodViewModel findById(Long id) {
        return foodRepository
                .findById(id)
                .map(foodEntity -> {
                    FoodViewModel foodViewModel = modelMapper
                            .map(foodEntity, FoodViewModel.class);

                    foodViewModel.setStorelogoUrl(this.findEntityById(foodEntity.getId()).getStore().getLogoUrl());

                    return foodViewModel;
                })
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public FoodEntity findEntityById(Long foodId) {
        return foodRepository
                .findById(foodId)
                .orElseThrow(IllegalArgumentException::new);
    }





}
