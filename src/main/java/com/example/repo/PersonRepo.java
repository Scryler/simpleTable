package com.example.repo;

import com.example.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person,Integer> {

    Person findByid (String id);
    List<Person> findByfirstName (String firstName);
    List<Person> findBylastName (String lastName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
