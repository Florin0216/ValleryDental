package com.example.vallerydental.controller;

import com.example.vallerydental.model.Person;
import com.example.vallerydental.service.impl.PersonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {
    private final PersonServiceImpl personServiceImpl;

    public ContentController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String DisplayHomepage() {
        return "Home";
    }

    @GetMapping("/register")
    public String DisplayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "Register";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute("person") Person person) {
        personServiceImpl.addPerson(person);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String DisplayLoginPage() {
            return "Login";
    }

}
