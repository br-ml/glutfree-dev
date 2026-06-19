package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.service.FoodAddServiceModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequestMapping("/food")
@RestController
public class FoodRestController {

    private final FoodService foodService;

    public FoodRestController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/api")
    public ResponseEntity<List<FoodViewModel>> findAll() {
        return ResponseEntity.ok(foodService.findAllFoods());
    }

    @GetMapping("/api-tested")
    public ResponseEntity<List<FoodViewModel>> findAllTested() {
        return ResponseEntity.ok(foodService.findAllTestedFoods());
    }

    @GetMapping("/api/latest")
    public ResponseEntity<List<FoodViewModel>> findLatest6() {
        return ResponseEntity.ok(foodService.findLatest6Foods());
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<FoodViewModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.findById(id));
    }

    @GetMapping("/api/category/{category}")
    public ResponseEntity<List<FoodViewModel>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(foodService.findAllByCategory(category));
    }

    @GetMapping("/api/without-lactose")
    public ResponseEntity<List<FoodViewModel>> findWithoutLactose() {
        return ResponseEntity.ok(foodService.findGFAndWithoutLactose());
    }

    @PostMapping("/api")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addFood(
            @RequestParam String name,
            @RequestParam String brand,
            @RequestParam(required = false) String copyright,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String category,
            @RequestParam String store,
            @RequestParam(defaultValue = "false") boolean nimaTested,
            @RequestParam(defaultValue = "false") boolean markedAsGF,
            @RequestParam(defaultValue = "false") boolean withoutLactose,
            @RequestParam(defaultValue = "false") boolean glutenTox,
            @RequestParam(required = false) String pictureDate,
            @RequestParam MultipartFile image,
            @RequestParam MultipartFile labelImage) throws IOException {

        FoodAddServiceModel model = new FoodAddServiceModel();
        model.setName(name);
        model.setBrand(brand);
        model.setCopyright(copyright);
        model.setDetails(details);
        model.setCategory(category);
        model.setStore(store);
        model.setNimaTested(nimaTested);
        model.setMarkedAsGF(markedAsGF);
        model.setWithoutLactose(withoutLactose);
        model.setGlutenTox(glutenTox);
        model.setImage(image);
        model.setLabelImage(labelImage);
        if (pictureDate != null && !pictureDate.isEmpty()) {
            model.setPictureDate(LocalDate.parse(pictureDate));
        }
        foodService.addFood(model);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
