package com.example.controller;

import com.example.domain.Person;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PersonRepo personRepo;

    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @GetMapping("/personList")
    public String personList(Model model) {

        Iterable<Person> persons = personRepo.findAll();

        model.addAttribute("persons", persons);

        return "personList";
    }

    @PostMapping("filter")
    public String filter(Model model,
                         @RequestParam String firstName,
                         @RequestParam String lastName) {

        Iterable<Person> persons;

        if (!firstName.isEmpty() && lastName.isEmpty()) {
            persons = personRepo.findByfirstName(firstName);
        } else if (!lastName.isEmpty() && firstName.isEmpty()) {
            persons = personRepo.findBylastName(lastName);
        } else if (!firstName.isEmpty() && !lastName.isEmpty()) {
            persons = personRepo.findByFirstNameAndLastName(firstName, lastName);
        } else {
            persons = personRepo.findAll();
        }

        model.addAttribute("persons", persons);

        return "personList";

    }

}

