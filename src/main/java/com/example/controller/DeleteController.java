package com.example.controller;

import com.example.errorMessage.ErrorMessage;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class DeleteController {

    @Autowired
    private PersonRepo personRepo;

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request){

        String [] checkId = request.getParameterValues("check");

        List<Integer> list = new ArrayList();

        if(checkId != null) {

            Arrays.stream(checkId).forEach(id -> list.add(Integer.valueOf(id)));

            list.forEach(id -> personRepo.deleteById(id));
        }

        return "redirect:/personList";
    }
}
