package ru.netology.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hibernate.repository.AppRepository;
import ru.netology.hibernate.repository.PersonEntity.Person;
import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class AppController {
    private AppRepository appRepository;

    @Autowired
    public void setAppRepository(AppRepository appRepository) {
        this.appRepository = appRepository;
    }
    /**
     * fastCheck - GET http://localhost:24001/persons/by-city?city=Sochi
     */
    @GetMapping("persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam("city") String city) {
        return appRepository.getPersonByCity(city);
    }
    /**
     * fastCheck - GET http://localhost:24001/persons/younger?age=29
     */
    @GetMapping("persons/younger")
    public List<Person> getPersonAgeLessThan(@RequestParam("age") int age) {
        return appRepository.getPersonWhoseAgeLessThan(age);
    }
    /**
     * fastCheck - GET http://localhost:24001/persons/by-name-surname?name=petr&surname=petrov
     */
    @GetMapping("persons/by-name-surname")
    public List<Person> getPersonByNameAndSurname(@RequestParam("name") String name,
                                                  @RequestParam("surname") String surname) {
        List<Person> personList = new ArrayList<>(3);
        Person person = appRepository.getPersonByNameAndSurname(name, surname);
        if(person != null) {
            personList.add(person);
        }
        return personList;
    }
}
