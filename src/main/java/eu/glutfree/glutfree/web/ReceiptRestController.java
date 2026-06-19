package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.entities.enums.TypeOfMealsEnums;
import eu.glutfree.glutfree.model.service.ReceiptAddServiceModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.service.ReceiptService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/receipt")
@RestController
public class ReceiptRestController {

    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;

    public ReceiptRestController(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<ReceiptViewModel>> findAll() {
        return ResponseEntity.ok(receiptService.findAllReceipts());
    }

    @GetMapping("/api/latest")
    public ResponseEntity<List<ReceiptViewModel>> findLatest6() {
        return ResponseEntity.ok(receiptService.findLatestAdded6Receipts());
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<ReceiptViewModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(receiptService.findById(id));
    }

    @PostMapping("/api")
    public ResponseEntity<Void> addReceipt(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String productsList,
            @RequestParam(required = false) TypeOfMealsEnums typeOfMeal,
            @RequestParam(defaultValue = "0") int duration,
            @RequestParam MultipartFile image) throws IOException {

        ReceiptAddServiceModel model = new ReceiptAddServiceModel();
        model.setName(name);
        model.setDescription(description);
        model.setProductsList(productsList);
        model.setTypeOfMeal(typeOfMeal);
        model.setDuration(duration);
        model.setImage(image);
        receiptService.addReceipt(model);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}
