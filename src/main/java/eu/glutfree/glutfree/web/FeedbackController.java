package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.bindings.FeedbackAddBindingModel;
import eu.glutfree.glutfree.model.service.FeedbackAddServiceModel;
import eu.glutfree.glutfree.model.view.FeedbackViewModel;
import eu.glutfree.glutfree.model.view.ReceiptViewModel;
import eu.glutfree.glutfree.service.FeedbackService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/feedback")
public class FeedbackController {


    private final ModelMapper modelMapper;
    private final FeedbackService feedbackService;


    public FeedbackController(ModelMapper modelMapper, FeedbackService feedbackService) {

        this.modelMapper = modelMapper;
        this.feedbackService = feedbackService;
    }



    @GetMapping("/add")
    public String addFeedback(Model model) {

        if(!model.containsAttribute("feedbackAddBindingModel")) {
            model.addAttribute("feedbackAddBindingModel", new FeedbackAddBindingModel());
        }
        return "add-feedback";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid FeedbackAddBindingModel feedbackAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("feedbackAddBindingModel", feedbackAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.feedbackAddBindingModel", bindingResult);

            return "redirect:add";
        }

        feedbackService.addFeedback(modelMapper.map(feedbackAddBindingModel, FeedbackAddServiceModel.class));

        return "redirect:/feedback/";

    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {

            modelAndView.addObject("feedbacks", this.feedbackService.findLatestAdded6Feedbacks());
            modelAndView.setViewName("view-feedbacks");

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

        feedbackService.delete(id);

        return "redirect:/feedback/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        FeedbackViewModel feedbackViewModel = feedbackService.findById(id);

        model.addAttribute("feedback", feedbackViewModel);

        return "details-feedback";
    }




}
