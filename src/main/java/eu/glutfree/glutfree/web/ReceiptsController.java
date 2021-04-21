package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.bindings.ReceiptAddBindingModel;
import eu.glutfree.glutfree.model.bindings.UserRegistrationBindingModel;
import eu.glutfree.glutfree.model.service.ReceiptAddServiceModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.service.ReceiptService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/receipt")
public class ReceiptsController {

  private final ReceiptService receiptService;
  private final ModelMapper modelMapper;

  public ReceiptsController(ReceiptService receiptService, ModelMapper modelMapper) {
    this.receiptService = receiptService;
    this.modelMapper = modelMapper;
  }



  @GetMapping("/add")
  public String addReceipt(Model model) {

    if(!model.containsAttribute("receiptAddBindingModel")) {
      model.addAttribute("receiptAddBindingModel", new ReceiptAddBindingModel());
    }
    return "add-receipts";
  }


  @PostMapping("/add")
  public String addConfirm(@Valid ReceiptAddBindingModel receiptAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {

    if(bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("receiptAddBindingModel", receiptAddBindingModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.receiptAddBindingModel", bindingResult);

      return "redirect:add";
    }

    receiptService.addReceipt(modelMapper.map(receiptAddBindingModel, ReceiptAddServiceModel.class));

    return "redirect:/";

  }

  @GetMapping("/")
  public ModelAndView index(ModelAndView modelAndView) {

    modelAndView.addObject("receipts", this.receiptService.findLatestAdded6Receipts());
    modelAndView.setViewName("view-receipts");

    return modelAndView;
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id,
                       Model model) {

    receiptService.deleteReceipt(id);

    return "redirect:/receipt/";
  }


  @GetMapping("/details/{id}")
  public String details(@PathVariable Long id, Model model){

    ReceiptViewModel receiptViewModel = receiptService.findById(id);

    model.addAttribute("receipt", receiptViewModel);

    return "details-receipt";
  }




}
