package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/feedback")
@RestController
public class FeedbackRestController {

  private final ModelMapper modelMapper;
  private final FeedbackRepository feedbackRepository;

  public FeedbackRestController(ModelMapper modelMapper, FeedbackRepository feedbackRepository) {
    this.modelMapper = modelMapper;
      this.feedbackRepository = feedbackRepository;
  }



  @GetMapping("/api")
  public ResponseEntity<List<FeedbackViewModel>> findAll() {

    List<FeedbackViewModel> feedbackViewModels = feedbackRepository.
            findAll().
            stream().
            map(feedback -> {
                FeedbackViewModel viewModel = modelMapper.map(feedback, FeedbackViewModel.class);
              return viewModel;
            }).
            collect(Collectors.toList());

    return ResponseEntity
            .ok()
            .body(feedbackViewModels);
  }



}