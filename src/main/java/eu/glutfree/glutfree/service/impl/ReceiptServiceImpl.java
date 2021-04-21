package eu.glutfree.glutfree.service.impl;

import eu.glutfree.glutfree.exceptions.ReceiptNotFoundException;
import eu.glutfree.glutfree.exceptions.UserNotFoundException;
import eu.glutfree.glutfree.model.entities.ReceiptEntity;
import eu.glutfree.glutfree.model.service.ReceiptAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.repository.ReceiptRepository;
import eu.glutfree.glutfree.service.CloudinaryService;
import eu.glutfree.glutfree.service.ReceiptService;
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
public class ReceiptServiceImpl implements ReceiptService {

    private final ModelMapper modelMapper;
    private final ReceiptRepository receiptRepository;
    private final UserService userService;
    private final CloudinaryService cloudinaryService;


    public ReceiptServiceImpl(ModelMapper modelMapper, ReceiptRepository receiptRepository, UserService userService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.receiptRepository = receiptRepository;

        this.userService = userService;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public void addReceipt(ReceiptAddServiceModel receiptAddServiceModel) throws IOException {

        MultipartFile img = receiptAddServiceModel.getImage();
        String imageUrl = cloudinaryService.uploadImage(img);

        ReceiptEntity receipt = modelMapper.map(receiptAddServiceModel, ReceiptEntity.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        receipt.setUser(userService.findUserByUsername(authentication.getName()));
        receipt.setUrlToPic(imageUrl);
        receiptRepository.save(receipt);

    }

    @Override
    public List<ReceiptViewModel> findAllReceipts() {

        return this.receiptRepository.findAll().stream().map(recepitEntity -> {
            ReceiptViewModel receiptViewModel = this.modelMapper.map(recepitEntity, ReceiptViewModel.class);
            return receiptViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptViewModel> findLatestAdded6Receipts() {

        return this.receiptRepository.findTop6ByIdIsNotNullOrderByIdDesc().stream().map(recepitEntity -> {
            ReceiptViewModel receiptViewModel = this.modelMapper.map(recepitEntity, ReceiptViewModel.class);
            return receiptViewModel;
        }).collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        receiptRepository.deleteById(id);

    }

    @Override
    public void deleteReceipt(Long id) {
        ReceiptEntity lessonEntity = this.receiptRepository.findById(id).orElseThrow(() -> new ReceiptNotFoundException("This receipt does not exist in the database!"));
        this.receiptRepository.delete(lessonEntity);


    }

    @Override
    public ReceiptViewModel findById(Long id) {
        return receiptRepository.findById(id).map(receiptEntity -> {
            ReceiptViewModel receiptViewModel = modelMapper.map(receiptEntity,ReceiptViewModel.class);

            return receiptViewModel;
        }).orElseThrow(IllegalArgumentException::new);
    }

}
