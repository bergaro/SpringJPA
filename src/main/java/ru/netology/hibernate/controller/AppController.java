package ru.netology.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hibernate.repository.AppRepository;
import ru.netology.hibernate.repository.PersonEntity.Person;

import java.util.List;

@RestController("/")
public class AppController {
    private AppRepository appRepository;

    @Autowired
    public void setAppRepository(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    /**
     * fastCheck - GET http://localhost:24001/persons/by-city?city=Moscow
     */
    @GetMapping("/persons/by-city")
    public List<Person> personsByCity(@RequestParam("city") String city) {
        return appRepository.personTrans(city);
    }

}
