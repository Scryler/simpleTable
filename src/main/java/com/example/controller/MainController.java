package com.example.controller;

import com.example.controllerService.MainService;
import com.example.domain.Person;
import com.example.errorMessage.ErrorMessage;
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
    MainService mainService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/personList")
    public String personList(Model model) {

        Iterable<Person> persons = mainService.getAllPerson();

        model.addAttribute("persons", persons);

        return "personList";
    }

    @PostMapping("filter")
    public String filter(Model model,
                         @RequestParam String firstName,
                         @RequestParam String lastName) {

        model.addAttribute("persons", mainService.getPersonsByFilter(firstName,lastName));

        return "personList";

    }

}

