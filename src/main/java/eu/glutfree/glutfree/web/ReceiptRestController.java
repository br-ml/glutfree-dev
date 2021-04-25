package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.repository.ReceiptRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/receipt")
@RestController
public class ReceiptRestController {

  private final ModelMapper modelMapper;
  private final ReceiptRepository receiptRepository;

  public ReceiptRestController(ModelMapper modelMapper, ReceiptRepository receiptRepository) {
    this.modelMapper = modelMapper;
    this.receiptRepository = receiptRepository;
  }



  @GetMapping("/api")
  public ResponseEntity<List<ReceiptViewModel>> findAll() {

    List<ReceiptViewModel> receiptViewModels = receiptRepository.
            findAll().
            stream().
            map(receipt -> {
                ReceiptViewModel viewModel = modelMapper.map(receipt, ReceiptViewModel.class);
              return viewModel;
            }).
            collect(Collectors.toList());

    return ResponseEntity
            .ok()
            .body(receiptViewModels);
  }


}