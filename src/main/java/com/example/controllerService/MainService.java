package com.example.controllerService;

import com.example.domain.Person;
import com.example.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainService {

    @Autowired
    private PersonRepo personRepo;


    public Iterable<Person> getAllPerson(){

        return personRepo.findAll();

    }

    public Iterable<Person> getPersonsByFilter(String firstName, String lastName){


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

        return persons;

    }

}
