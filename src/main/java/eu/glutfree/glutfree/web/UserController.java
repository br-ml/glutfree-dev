package eu.glutfree.glutfree.web;

import eu.glutfree.glutfree.model.bindings.UserLoginBindingModel;
import eu.glutfree.glutfree.model.bindings.UserRegistrationBindingModel;
import eu.glutfree.glutfree.model.bindings.UserUpdateBindingModel;
import eu.glutfree.glutfree.model.service.UserRegistrationServiceModel;
import eu.glutfree.glutfree.model.service.UserUpdateServiceModel;
import eu.glutfree.glutfree.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;


    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/login")
//    public ModelAndView login(Model model) {
//        if(!model.containsAttribute("userLoginBindingModel")) {
//            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
//            model.addAttribute("notFound", false);
//        }
//        return new ModelAndView("login");
//    }



    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/login-error")
    public ModelAndView failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                            String username, RedirectAttributes attributes) {
        ModelAndView modelAndView = new ModelAndView();

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", username);

        modelAndView.setViewName("redirect:/users/login");

        return modelAndView;
    }


    @ModelAttribute("registrationBindingModel")
    public UserRegistrationBindingModel createBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @PostMapping("/register")
    public String registerAndLoginUser(
            @Valid UserRegistrationBindingModel registrationBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.userNameExists(registrationBindingModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationBindingModel", registrationBindingModel);
            redirectAttributes.addFlashAttribute("userExistsError", true);

            return "redirect:/users/register";
        }

        UserRegistrationServiceModel userServiceModel = modelMapper
                .map(registrationBindingModel, UserRegistrationServiceModel.class);

        userService.registerAndLoginUser(userServiceModel);

        return "redirect:/home";
    }

    @GetMapping("/update")
    public String updateUser() {
        return "update-user";
    }

    @ModelAttribute("userUpdateBindingModel")
    public UserUpdateBindingModel updateBindingModel() {
        return new UserUpdateBindingModel();
    }

    @PostMapping("/update")
    public String updateUser(
            @Valid UserUpdateBindingModel userUpdateBindingModel,
            BindingResult bindingResult,
            Principal principal,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userUpdateBindingModel", userUpdateBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userUpdateBindingModel", bindingResult);

            return "redirect:/users/update";
        }


        UserRegistrationServiceModel userRegistrationServiceModel = modelMapper
                .map(userUpdateBindingModel, UserRegistrationServiceModel.class);
        userRegistrationServiceModel.setId(userService.findUserByUsername(principal.getName()).getId());

        userService.updateUser(userRegistrationServiceModel);

        return "redirect:/home";
    }










}