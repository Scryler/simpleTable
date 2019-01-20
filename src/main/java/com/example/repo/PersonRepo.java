package com.example.repo;

import com.example.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Integer> {

    @Modifying
    @Query("UPDATE Person p SET p.firstName =:firstName , p.lastName =:lastName WHERE p.id =:id")
    void updateFirstNameAndLastName(@Param("firstName") String firstName,
                                    @Param("lastName") String lastName,
                                    @Param("id") Integer id);


    List<Person> findByfirstName (String firstName);
    List<Person> findBylastName (String lastName);
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
