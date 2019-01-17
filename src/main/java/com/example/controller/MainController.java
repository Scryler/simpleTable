package com.example.controller;

import java.util.ArrayList;
import java.util.List;


import com.example.domain.Person;
import com.example.domain.PersonForm;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private PersonRepo personRepo;


    //private static List<Person> persons = new ArrayList<Person>();

/*    static {
        persons.add(new Person("Bill", "Gates"));
        persons.add(new Person("Steve", "Jobs"));
    }*/

    // ​​​​​​​
    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/personList" }, method = RequestMethod.GET)
    public String personList(Model model) {

        Iterable<Person> persons = personRepo.findAll();

        model.addAttribute("persons", persons);

        //model.addAttribute("persons", persons);

        return "personList";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        PersonForm personForm = new PersonForm();

        model.addAttribute("personForm", personForm);

        return "addPerson";
    }

    @RequestMapping(value = { "/addPerson" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("personForm") PersonForm personForm,
                             @RequestParam(value = "action") String action) {

        String firstName = personForm.getFirstName();
        String lastName = personForm.getLastName();

        if (firstName != null && firstName.length() > 0 //
                && lastName != null && lastName.length() > 0 && action.equals("Create")) {
            Person newPerson = new Person(firstName, lastName);
            //persons.add(newPerson);

            personRepo.save(newPerson);

            Iterable<Person> persons = personRepo.findAll();

            model.addAttribute("persons", persons);

            return "redirect:/personList";
        }

        if(action.equals("Cancel"))
            return "redirect:/personList";

        model.addAttribute("errorMessage", errorMessage);
        return "addPerson";
    }

}
