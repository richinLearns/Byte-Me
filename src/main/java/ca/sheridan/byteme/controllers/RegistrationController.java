package ca.sheridan.byteme.controllers;

import ca.sheridan.byteme.beans.Role;
import ca.sheridan.byteme.models.RegistrationForm;
import ca.sheridan.byteme.repositories.UserRepository;
import ca.sheridan.byteme.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

  private final UserRepository userRepository;
  private final UserService userService;

  @GetMapping("/register")
  public String showForm(Model model) {
    if (!model.containsAttribute("form")) {
      model.addAttribute("form", new RegistrationForm());
    }
    return "register";
  }

  @PostMapping("/register")
  public String handleRegister(@Valid RegistrationForm form,
                               BindingResult binding,
                               RedirectAttributes ra) {
    if (!form.getPassword().equals(form.getConfirmPassword())) {
      binding.rejectValue("confirmPassword", "mismatch", "Passwords do not match");
    }
    userRepository.findByEmail(form.getEmail())
        .ifPresent(u -> binding.rejectValue("email", "exists", "Email already registered"));

    if (binding.hasErrors()) {
      ra.addFlashAttribute("org.springframework.validation.BindingResult.form", binding);
      ra.addFlashAttribute("form", form);
      return "redirect:/register";
    }

    userService.createUser(form.getEmail(), form.getPassword(), Role.CUSTOMER);
    ra.addFlashAttribute("registered", true);
    return "redirect:/login";
  }
}
