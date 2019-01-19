package com.example.controller;

import com.example.domain.Person;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EditController {

    @Autowired
    private PersonRepo personRepo;

    private Integer id;

    @PostMapping("/edit")
    public String edit(Model model,
                       HttpServletRequest request,
                       @ModelAttribute("person") Person person){

        String [] checkId = request.getParameterValues("check");

        List<Integer> list = new ArrayList();

        if(checkId != null) {
            Arrays.stream(checkId).forEach(id -> list.add(Integer.valueOf(id)));
        }
        if(list.size() == 1){
            id = list.get(0);
            model.addAttribute("person", personRepo.findById(list.get(0)));
            return "/edit";
        } else return "redirect:/personList";
    }

    @PostMapping("/accept")
    public String accept(Model model,
                         @ModelAttribute("person") Person person,
                         @RequestParam(value = "action") String action){

        if(action.equals("Accept") &&
                person.getFirstName() != null &&
                person.getLastName() != null) {
            personRepo.updateFirstName(person.getFirstName(),id);
            personRepo.updateLastName(person.getLastName(),id);
            //Person editPerson = new Person(person.getFirstName(), person.getLastName());
            /*person.setFirstName(person.getFirstName());
            person.setLastName(person.getLastName());*/
            //personRepo.deleteById(id);

            /*personRepo.save(person);*/

            return "redirect:/personList";
        }
        return "redirect:/personList";
    }
}
