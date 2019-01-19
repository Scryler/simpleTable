package com.example.controller;

import com.example.domain.Person;
import com.example.domain.PersonForm;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddPersonController {

    @Autowired
    private PersonRepo personRepo;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping("/addPerson")
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();

        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String savePerson(Model model,
                             @ModelAttribute("personForm") PersonForm personForm,
                             @RequestParam(value = "action") String action) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0
                && lastName != null && lastName.length() > 0
                && action.equals("Create")) {
            Person newPerson = new Person(firstName, lastName);

            personRepo.save(newPerson);

            Iterable<Person> persons = personRepo.findAll();

            model.addAttribute("persons", persons);

            return "redirect:/personList";
        }

        if (action.equals("Cancel"))
            return "redirect:/personList";

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }
}
