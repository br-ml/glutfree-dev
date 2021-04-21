package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.bindings.FoodAddBindingModel;
import eu.glutfree.glutfree.model.service.FoodAddServiceModel;
import eu.glutfree.glutfree.model.view.FoodViewModel;
import eu.glutfree.glutfree.service.FoodService;
import eu.glutfree.glutfree.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping("/food")
public class FoodController {



    private final FoodService foodService;
    private final ModelMapper modelMapper;
    private final StoreService storeService;
//    private final LogService logService;

    public FoodController(FoodService foodService, ModelMapper modelMapper, StoreService storeService) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
        this.storeService = storeService;
//        this.logService = logService;
    }



    @GetMapping("/add")
    public String addFood(Model model) {

        if(!model.containsAttribute("foodAddBindingModel")) {
            model.addAttribute("foodAddBindingModel", new FoodAddBindingModel());
        }

        model.addAttribute("stores", storeService.findAllStores());

        return "add-food";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid FoodAddBindingModel foodAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("foodAddBindingModel", foodAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.foodAddBindingModel", bindingResult);

            return "redirect:add";
        }

        foodService.addFood(modelMapper.map(foodAddBindingModel, FoodAddServiceModel.class));

        return "redirect:/food/";

    }


    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.addObject("foods", this.foodService.findAllFoods());
        modelAndView.addObject("latest6foods", this.foodService.findLatest6Foods());
//        modelAndView.addObject("topThreeVisitedFood", this.logService.findTopThreeViewedFoods());


        modelAndView.setViewName("view-foods");

        return modelAndView;
    }

    @GetMapping("/tested")
    public ModelAndView indexNima(ModelAndView modelAndView) {

        modelAndView.addObject("latest6testedFoods", this.foodService.findLatest6TestedFoods());
        modelAndView.setViewName("view-testedFoods");

        return modelAndView;
    }



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         Model model) {

//        logService.deleteAllById(id);
        foodService.delete(id);


        return "redirect:/food/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model){

        FoodViewModel foodViewModel = foodService.findById(id);
        model.addAttribute("food", foodViewModel);
        return "details-food";
    }





}
