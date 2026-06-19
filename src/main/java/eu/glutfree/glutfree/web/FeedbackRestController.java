package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.entities.enums.TypeOfPlaceEnums;
import eu.glutfree.glutfree.model.service.FeedbackAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/feedback")
@RestController
public class FeedbackRestController {

    private final FeedbackService feedbackService;
    private final ModelMapper modelMapper;

    public FeedbackRestController(FeedbackService feedbackService, ModelMapper modelMapper) {
        this.feedbackService = feedbackService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<FeedbackViewModel>> findAll() {
        return ResponseEntity.ok(feedbackService.findAllFeedbacks());
    }

    @GetMapping("/api/latest")
    public ResponseEntity<List<FeedbackViewModel>> findLatest6() {
        return ResponseEntity.ok(feedbackService.findLatestAdded6Feedbacks());
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<FeedbackViewModel> findById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.findById(id));
    }

    @PostMapping("/api")
    public ResponseEntity<Void> addFeedback(
            @RequestParam String name,
            @RequestParam int score,
            @RequestParam String feedbackText,
            @RequestParam(required = false) String webSiteUrl,
            @RequestParam(required = false) TypeOfPlaceEnums typeOfPlace,
            @RequestParam MultipartFile image) throws IOException {

        FeedbackAddServiceModel model = new FeedbackAddServiceModel();
        model.setName(name);
        model.setScore(score);
        model.setFeedbackText(feedbackText);
        model.setWebSiteUrl(webSiteUrl);
        model.setTypeOfPlace(typeOfPlace);
        model.setImage(image);
        feedbackService.addFeedback(model);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
